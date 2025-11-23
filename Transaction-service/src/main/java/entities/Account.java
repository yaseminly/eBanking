package entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Account {
@Id
    private Long id;
    private double balance;
}
