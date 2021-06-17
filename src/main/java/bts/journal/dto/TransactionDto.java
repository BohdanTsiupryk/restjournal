package bts.journal.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {

    long id;

    long from;

    BigDecimal amount;

    long to;

    Date date;

    String message;
}
