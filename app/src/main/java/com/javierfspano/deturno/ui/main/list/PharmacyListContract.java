package com.javierfspano.deturno.ui.main.list;

import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.ui.base.BaseContract;

import java.util.List;

public interface PharmacyListContract {

    interface View extends BaseContract.BaseView {
        void setList(List<Pharmacy> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
    }
}
