package com.lmx.common.Base;

/**
 * V层的公共接口
 */
public interface IBaseView{

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 关闭加载框
     */
    void dismissLoading();

    /**
     * 显示吐司
     *
     * @param message 显示内容
     */
    void showToast(String message);

    /**
     * 显示错误信息
     *
     * @param tag 标签
     * @param errorMsg 错误信息
     */
    void onError(Object tag, String errorMsg);

}
