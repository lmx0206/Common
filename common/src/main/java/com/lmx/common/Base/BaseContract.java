package com.lmx.common.Base;

/**
 * M层V层P层的父类
 *
 * @param <C> 契约接口
 */
public abstract class BaseContract<C> {

    /**
     * 获得契约接口
     *
     * @return 契约接口
     */
    protected abstract C getContract();

}
