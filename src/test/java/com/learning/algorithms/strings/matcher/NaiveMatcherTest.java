package com.learning.algorithms.strings.matcher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class NaiveMatcherTest {

    private NaiveMatcher matcher;

    @BeforeEach
    public void setUp() {
        matcher = new NaiveMatcher();
    }

    @Test
    public void returnsAllMatchesWhenThereAreSome() {
        List<Integer> matches = matcher.match("panamabananas", "ana");

        assertThat(matches, containsInAnyOrder(1, 7, 9));
    }

    @Test
    public void returnsNoMatchesWhenThereAreNone() {
        List<Integer> matches = matcher.match("panamabananas", "paa");

        assertThat(matches, equalTo(Collections.emptyList()));
    }

    @Test
    public void returnsMatchFromTheStart() {
        List<Integer> matches = matcher.match("anamabanenas", "ana");

        assertThat(matches, equalTo(Collections.singletonList(0)));
    }

    @Test
    public void returnsMatchToTheEnd() {
        List<Integer> matches = matcher.match("benana", "ana");

        assertThat(matches, equalTo(Collections.singletonList(3)));
    }
}
