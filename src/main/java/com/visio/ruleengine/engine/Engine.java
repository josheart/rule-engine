package com.visio.ruleengine.engine;

import com.visio.ruleengine.models.Data;

public interface Engine {

    Data applyRules(Data data);
}
