package com.tfr.rules.compare;

import com.tfr.rules.model.Rule;

import java.util.Comparator;

/**
 * Sort rules by the highest priority descending.
 *
 * Created by Erik on 12/7/2016.
 */
public class RulePriorityComparator implements Comparator<Rule> {

    public int compare(Rule o1, Rule o2) {
        return o1.getPriority() - o2.getPriority();
    }

}
