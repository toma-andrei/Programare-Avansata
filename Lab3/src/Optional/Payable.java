package Optional;
/***
 * interfata Payable. Obliga clasele ce o implementeaza sa suprascrie TOATE metodele din ea (cu exceptia cand clasa e abstracta).
 */
public interface Payable {

    public boolean isPayable();
    public int getEntryFee();
}
