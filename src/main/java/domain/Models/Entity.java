package domain.Models;

public interface Entity<ID> {
    ID getId();
    void setID(ID id);
}
