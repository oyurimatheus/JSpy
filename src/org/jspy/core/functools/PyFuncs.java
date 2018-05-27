package org.jspy.core.functools;

import java.util.Map;
import java.util.Set;

public interface PyFuncs {
    Map<String, Object> vars(Object obj);

    Set<String> dir(Object obj);


}
