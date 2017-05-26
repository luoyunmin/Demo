package com.yunmin.binderpool;

import android.os.RemoteException;

/**
 * Created by luoyunmin on 2016/8/29.
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
