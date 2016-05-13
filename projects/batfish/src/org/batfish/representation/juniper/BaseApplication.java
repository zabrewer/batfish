package org.batfish.representation.juniper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.batfish.common.util.ComparableStructure;
import org.batfish.main.Warnings;
import org.batfish.representation.IpAccessListLine;

public class BaseApplication extends ComparableStructure<String> implements
      Application {

   public static class Term extends ComparableStructure<String> {

      /**
       *
       */
      private static final long serialVersionUID = 1L;

      private final IpAccessListLine _line;

      public Term(String name) {
         super(name);
         _line = new IpAccessListLine();
      }

      public void applyTo(IpAccessListLine destinationLine) {
         destinationLine.getProtocols().addAll(_line.getProtocols());
         destinationLine.getDstPortRanges().addAll(_line.getDstPortRanges());
         destinationLine.getSrcPortRanges().addAll(_line.getSrcPortRanges());
      }

      public IpAccessListLine getLine() {
         return _line;
      }

   }

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private boolean _ipv6;

   private Term _mainTerm;

   private final Map<String, Term> _terms;

   public BaseApplication(String name) {
      super(name);
      _mainTerm = new Term(getMainTermName());
      _terms = new LinkedHashMap<String, Term>();
   }

   @Override
   public void applyTo(IpAccessListLine srcLine, List<IpAccessListLine> lines,
         Warnings w) {
      Collection<Term> terms;
      if (_terms.isEmpty()) {
         terms = Collections.singletonList(_mainTerm);
      }
      else {
         terms = _terms.values();
      }
      for (Term term : terms) {
         IpAccessListLine newLine = new IpAccessListLine();
         newLine.getDstIpWildcards().addAll(srcLine.getDstIpWildcards());
         newLine.getSrcIpWildcards().addAll(srcLine.getSrcIpWildcards());
         newLine.setAction(srcLine.getAction());
         term.applyTo(newLine);
         lines.add(newLine);
      }
   }

   @Override
   public boolean getIpv6() {
      return _ipv6;
   }

   public Term getMainTerm() {
      return _mainTerm;
   }

   private String getMainTermName() {
      return "~MAIN_TERM~" + _key;
   }

   public Map<String, Term> getTerms() {
      return _terms;
   }

   public void setIpv6(boolean ipv6) {
      _ipv6 = true;
   }

}
