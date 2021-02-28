package com.company;

/***
 * interfata Visitable. Obliga clasele ce o implementeaza sa suprascrie TOATE metodele din ea (cu exceptia cand clasa e abstracta).
 */

public interface Visitable {
    public boolean isOpened();
    public String getOpeningHour();
}
