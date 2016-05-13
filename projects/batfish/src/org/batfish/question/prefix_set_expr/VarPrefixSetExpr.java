package org.batfish.question.prefix_set_expr;

import java.util.Set;

import org.batfish.datamodel.Prefix;
import org.batfish.question.Environment;

public final class VarPrefixSetExpr extends BasePrefixSetExpr {

   private final String _var;

   public VarPrefixSetExpr(String var) {
      _var = var;
   }

   @Override
   public Set<Prefix> evaluate(Environment environment) {
      return environment.getPrefixSets().get(_var);
   }

}
