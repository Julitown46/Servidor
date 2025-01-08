package org.iesbelen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.model.Departamento;

public class DepartamentoDAOImpl extends AbstractDAOImpl implements DepartamentoDAO {

    @Override
    public void create(Departamento departamento) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO departamento (nombre, presupuesto, gastos) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, departamento.getNombre());
            ps.setDouble(idx++, departamento.getPresupuesto());
            ps.setDouble(idx++, departamento.getGastos());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de departamento con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                departamento.setCodigo(rsGenKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Departamento> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Departamento> listDep = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM departamento");
            while (rs.next()) {
                Departamento dept = new Departamento();
                int idx = 1;
                dept.setCodigo(rs.getInt(idx++));
                dept.setNombre(rs.getString(idx++));
                dept.setPresupuesto(rs.getDouble(idx++));
                dept.setGastos(rs.getDouble(idx++));
                listDep.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listDep;

    }

    @Override
    public Optional<Departamento> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM departamento WHERE codigo = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Departamento dept = new Departamento();
                idx = 1;
                dept.setCodigo(rs.getInt(idx++));
                dept.setNombre(rs.getString(idx++));
                dept.setPresupuesto(rs.getDouble(idx++));
                dept.setGastos(rs.getDouble(idx++));

                return Optional.of(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void update(Departamento departamento) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Integer> getCantEmpleado(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT COUNT(*) FROM empleado WHERE codigo = ?");

            int idx = 1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                idx = 1;
                int count = rs.getInt(idx);
                return Optional.of(count);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public List<DepartamentoDTO> getAllDTO() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<DepartamentoDTO> listaDept = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("""
			SELECT codigo, nombre, presupuesto, gastos, 
				   (select count(*)  from  empleado e where e.codigo_departamento=d.codigo ) numero
			FROM departamento d 
			""");
            while (rs.next()) {
                DepartamentoDTO dept = new DepartamentoDTO();
                int idx = 1;
                dept.setCodigo(rs.getInt(idx++));
                dept.setNombre(rs.getString(idx++));
                dept.setPresupuesto(rs.getDouble(idx++));
                dept.setGastos(rs.getDouble(idx++));
                dept.setCantEmpleados(rs.getInt(idx++));
                listaDept.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaDept;

    }

    @Override
    public List<DepartamentoDTO> getOrdenadoDTO(int menor, int mayor) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<DepartamentoDTO> listDept = new ArrayList<>();

        try {
            conn = connectDB();

            String consulta = "SELECT codigo, nombre, presupuesto, gastos FROM departamento WHERE (presupuesto - gastos) >= menor AND (presupuesto - gastos) <= mayor";

            s = conn.createStatement();

            rs = s.executeQuery(consulta);
            while (rs.next()) {
                DepartamentoDTO dept = new DepartamentoDTO();
                int idx = 1;
                dept.setCodigo(rs.getInt(idx++));
                dept.setNombre(rs.getString(idx++));
                dept.setPresupuesto(rs.getDouble(idx++));
                dept.setGastos(rs.getDouble(idx++));
                dept.setCantEmpleados(rs.getInt(idx++));
                listDept.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listDept;

    }
}
