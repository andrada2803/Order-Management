package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class GenericDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    /**
     * Get the class
     */
    public GenericDAO(Class<T> type) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * The method which creates the "SELECT" query based by a condition
     * @param field - the field in the database table for the "WHERE" condition
     * @return - the query to be executed
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").
                append(type.getSimpleName()).
                append(" WHERE ").
                append(field).
                append(" =?");
        return sb.toString();
    }

    /**
     * The method which creates the "INSERT" query, going through all the fields of the table and inserting the values we
     * want
     * @return - the query to be executed
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").
                append(type.getSimpleName()).
                append("(");
        for (Field declaredField : type.getDeclaredFields()) {
            sb.append(declaredField.getName()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(") VALUES (");
        for (Field declaredField : type.getDeclaredFields()) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.lastIndexOf(",")).append(")");
        return sb.toString();
    }

    /**
     * The method which created the "DELETE" query
     * @param field - the field in the database table which we want to delete
     * @return - the query to be executed
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").
                append(type.getSimpleName()).
                append(" WHERE ").
                append(field).
                append(" =?");
        return sb.toString();
    }

    /**
     * The method which created the "EDIT/UPDATE" query, going through all the table's fields and assert them values through
     * "?"
     * @param field - the field in the database table which we want to edit
     * @return - the query to be executed
     */
    private String createEditQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").
                append(type.getSimpleName()).
                append(" SET ");
        for (Field declaredField : type.getDeclaredFields()) {
            sb.append(declaredField.getName()).append(" =?, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" WHERE ").
                append(field).
                append(" =?");
        return sb.toString();
    }

    /**
     * The method searches the object T through the database table, the object with the id send as a parameter, also the
     * connection with the database is made
     * @param id - the identification number of a type of object, like Client, Product or Orders
     * @return - The object
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException sqlException) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + sqlException.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * This method connect to the database, and store into the list all the object from the database table
     * @return - the list of the object type
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException sqlException) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + sqlException.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * This method connects to the database, and then go through all the columns of the table in which we want to insert the
     * object in order to execute the query
     * @param t - the object which will be inserted
     * @return - -1 if there was an exception, else return another integer
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int insertedId = -1;
        String query = createInsertQuery();
        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            for (Field declaredField : type.getDeclaredFields()) {
                declaredField.setAccessible(true);
                statement.setObject(index, declaredField.get(t));
                index++;
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                insertedId = resultSet.getInt(1);
            }

        } catch (SQLException | IllegalAccessException sqlException) {
            LOGGER.log(Level.WARNING, "DAO:insert " + sqlException.getMessage());
            return -1;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }

    /**
     * The method connects to the database and prepare the query for the delete operation
     * @param id - the identification number of the object
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException sqlException) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + sqlException.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This methods connects to the database and prepare the query for the edit operation
     * @param t - the object which will be edited
     * @return - -1 in case of an exception, another integer otherwise
     */
    public int edit(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int insertedId = -1;
        String query = createEditQuery("id");
        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            for (Field declaredField : type.getDeclaredFields()) {
                declaredField.setAccessible(true);
                statement.setObject(index, declaredField.get(t));
                index++;
            }
            Field id = type.getDeclaredField("id");
            id.setAccessible(true);
            statement.setObject(index, id.get(t));

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                insertedId = resultSet.getInt(1);
            }

        } catch (SQLException | IllegalAccessException | NoSuchFieldException sqlException) {
            LOGGER.log(Level.WARNING, "DAO:edit " + sqlException.getMessage());
            return -1;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
