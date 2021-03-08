package com.lmx.common.Base;

/**
 * P层的基类，所有P层都需要继承它
 *
 * @param <M> M层
 * @param <V> V层
 * @param <Contract> 契约
 */
public abstract class BasePresenter<M extends BaseModel, V extends IBaseView, Contract>
        extends BaseContract<Contract> {

    private M model;
    private V view;

    public BasePresenter() {
        this.model = createModelInstance();
    }

    /**
     * 创建Model实例
     *
     * @return 返回Model
     */
    protected abstract M createModelInstance();

    public M getModel() {
        return model;
    }

    public V getView() {
        return view;
    }

    /**
     * 与V层进行连接
     *
     * @param view V层
     */
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * 与V层断开连接
     */
    public void detachView() {
        model = null;
        if (!isAttachView()) {
            view = null;
        }
    }

    /**
     * 和V层是否连接
     *
     * @return true 连接， false 断开连接
     */
    protected boolean isAttachView() {
        return view != null;
    }

}
