package com.gogent.impl;

import com.gogent.annotation.Example;
import com.gogent.interfaces.AbstractExampleExecutable;

@Example(command = "exit, quit, over, stop", ref = ExitExecutor.class, description = "退出程序")
public class ExitExecutor extends AbstractExampleExecutable {
    public void process() {
        System.exit(0);
    }
}
