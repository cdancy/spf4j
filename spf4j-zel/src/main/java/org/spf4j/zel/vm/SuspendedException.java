/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spf4j.zel.vm;

import org.spf4j.concurrent.FutureBean;

public final class SuspendedException extends Exception {

    private final FutureBean<Object> suspendedAt;

    public SuspendedException(final FutureBean<Object> suspendedAt) {
        this.suspendedAt = suspendedAt;
    }

    public FutureBean<Object> getSuspendedAt() {
        return suspendedAt;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
