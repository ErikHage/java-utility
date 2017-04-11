package com.tfr.rules.model;

import com.tfr.rules.compare.RulePriorityComparator;

import java.util.*;

/**
 *
 * Created by Erik on 12/7/2016.
 */
public abstract class RuleSet<T,R> {

    private List<Rule<T,R>> rules;
    private Comparator<Rule> priorityComparator;

    public RuleSet() {
        rules = new ArrayList<Rule<T,R>>();
        priorityComparator = new RulePriorityComparator();
    }

    public void addRule(Rule<T,R> rule) {
        rules.add(rule);
        Collections.sort(rules, priorityComparator);
    }

    public Optional<Rule<T,R>> match(T t) {
        return rules.stream()
                .filter((rule) -> rule.evaluate(t))
                .findFirst();
    }




}
