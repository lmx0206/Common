package com.lmx.common.Base;

public abstract class BaseModel<P extends BasePresenter, Contract>
        extends BaseContract<Contract>
        implements IBaseModel {

    public P presenter;

    public BaseModel(P presenter) {
        this.presenter = presenter;
    }
}
