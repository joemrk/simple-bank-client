package currency;

import database.Repository;

public class CurrencyRepository extends Repository<Currency> {

  public CurrencyRepository() {
    super(Currency.class);
  }
}
