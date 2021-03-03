package com.company;

/***
 * interfata Visitable. Obliga clasele ce o implementeaza sa suprascrie TOATE metodele din ea (cu exceptia cand clasa e abstracta).
 */

public interface Visitable {
     boolean isOpened();
     String getOpeningHour();
}
