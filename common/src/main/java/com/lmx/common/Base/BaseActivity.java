package com.lmx.common.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewbinding.ViewBinding;

import com.lmx.common.util.ToastUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Activity的基类， 所有Activity都需要继承此类
 *
 * @param <VIEWBINDING> ViewBinding
 * @param <P> P层
 * @param <Contract> 契约接口
 */
public abstract class BaseActivity<VIEWBINDING extends ViewBinding, P extends BasePresenter, Contract>
        extends AppCompatActivity
        implements IBaseView {

    public VIEWBINDING viewbinding;
    public P presenter;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Class<?> clz = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method inflate = clz.getDeclaredMethod("inflate", LayoutInflater.class);
                viewbinding = (VIEWBINDING) inflate.invoke(null, getLayoutInflater());
                setContentView(viewbinding.getRoot());
            } catch (Exception e) {
                onError(e, e.getMessage());
            }
        }
        presenter = createPresenterInstance();
        presenter.attachView(this);
        initListener();
    }

    /**
     * 创建P层实例
     *
     * @return P层实例
     */
    protected abstract P createPresenterInstance();

    protected abstract Contract getContract();

    protected abstract void initListener();

    @Override
    public void showLoading() {
        runOnUiThread(() -> {

        });
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showToast(String message) {
        runOnUiThread(() -> ToastUtils.show(context, message));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
        destroy();
    }

    protected abstract void destroy();
}
