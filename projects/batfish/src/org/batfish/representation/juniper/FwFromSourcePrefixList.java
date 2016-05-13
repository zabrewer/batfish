package org.batfish.representation.juniper;

import org.batfish.common.BatfishException;
import org.batfish.main.Warnings;
import org.batfish.representation.Configuration;
import org.batfish.representation.IpAccessListLine;
import org.batfish.representation.IpWildcard;
import org.batfish.representation.LineAction;
import org.batfish.representation.RouteFilterLine;
import org.batfish.representation.RouteFilterList;

public final class FwFromSourcePrefixList extends FwFrom {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private final String _name;

   public FwFromSourcePrefixList(String name) {
      _name = name;
   }

   @Override
   public void applyTo(IpAccessListLine line, JuniperConfiguration jc,
         Warnings w, Configuration c) {
      PrefixList pl = jc.getPrefixLists().get(_name);
      if (pl != null) {
         pl.getReferers().put(this, "firewall from source-prefix-list");
         if (pl.getIpv6()) {
            return;
         }
         RouteFilterList sourcePrefixList = c.getRouteFilterLists().get(_name);
         for (RouteFilterLine rfLine : sourcePrefixList.getLines()) {
            if (rfLine.getAction() != LineAction.ACCEPT) {
               throw new BatfishException(
                     "Expected accept action for routerfilterlist from juniper");
            }
            else {
               line.getSrcIpWildcards().add(new IpWildcard(rfLine.getPrefix()));
            }
         }
      }
      else {
         w.redFlag("Reference to undefined source prefix-list: \"" + _name
               + "\"");
      }
   }

}
