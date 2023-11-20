package Logic;

import Model.opinionType;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OpinionServiceInterface {
    void addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion) throws SQLException, ClassNotFoundException;
    int setIndex(int id);
    void deleteOpinion(int id, int number) throws SQLException, ClassNotFoundException;
    void showPerson(int id);
    void showAll();

}
