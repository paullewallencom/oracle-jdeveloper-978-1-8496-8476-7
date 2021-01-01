package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class ExtViewObjectImpl extends ViewObjectImpl {
    public ExtViewObjectImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public ExtViewObjectImpl() {
        super();
    }
}
