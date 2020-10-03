package com.github.ros.roxanne_sa.ai.framework.compiler.ddl.v3.parser;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

/**
 *
 * @author Riccardo De Benedictis
 */
public abstract class DDLComponentType extends CommonTree {

    protected String name;

    DDLComponentType(Token payload) {
        super(payload);
    }

    abstract void parse();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
