package org.batfish.representation.cisco;

import java.io.Serializable;

import org.batfish.datamodel.Prefix;

public class BgpAggregateNetwork implements Serializable {

   private static final long serialVersionUID = 1L;

   private boolean _asSet;

   private String _attributeMap;

   private Prefix _prefix;

   private boolean _summaryOnly;

   public BgpAggregateNetwork(Prefix prefix) {
      _prefix = prefix;
   }

   @Override
   public boolean equals(Object o) {
      BgpAggregateNetwork rhs = (BgpAggregateNetwork) o;
      return _prefix.equals(rhs._prefix);
   }

   public boolean getAsSet() {
      return _asSet;
   }

   public String getAttributeMap() {
      return _attributeMap;
   }

   public Prefix getPrefix() {
      return _prefix;
   }

   public boolean getSummaryOnly() {
      return _summaryOnly;
   }

   @Override
   public int hashCode() {
      return _prefix.hashCode();
   }

   public void setAsSet(boolean asSet) {
      _asSet = asSet;
   }

   public void setAttributeMap(String attributeMap) {
      _attributeMap = attributeMap;
   }

   public void setSummaryOnly(boolean summaryOnly) {
      _summaryOnly = summaryOnly;
   }

}
