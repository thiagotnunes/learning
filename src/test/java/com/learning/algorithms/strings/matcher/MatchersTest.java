package com.learning.algorithms.strings.matcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class MatchersTest {
    @Parameterized.Parameters(name = "{0}")
    public static Object[] matchers() {
        return new Object[]{
                new NaiveMatcher(),
                new RabinKarpMatcher(),
                new KnuthMorrisPrattMatcher(),
                new SuffixTrieMatcher()
        };
    }

    @Parameterized.Parameter
    public Matcher matcher;

    @Test
    public void returnsAllMatchesWhenThereAreSome() {
        List<Integer> matches = matcher.match("panamabananas", "ana");

        assertThat(matches, containsInAnyOrder(1, 7, 9));
    }

    @Test
    public void returnsMatchWhenPatternIsEqualToText() {
        List<Integer> matches = matcher.match("panamabananas", "panamabananas");

        assertThat(matches, equalTo(Collections.singletonList(0)));
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

    @Test
    public void returnsNoMatchesWhenPatternIsLongerThanText() {
        List<Integer> matches = matcher.match("panamabananas", "panamabananass");

        assertThat(matches, equalTo(Collections.emptyList()));
    }

    @Test
    public void returnsNoMatchesWhenPatternIsEmpty() {
        List<Integer> matches = matcher.match("panamabananas", "");

        assertThat(matches, equalTo(Collections.emptyList()));
    }

    @Test
    public void returnsNoMatchesWhenTextIsEmpty() {
        List<Integer> matches = matcher.match("", "ana");

        assertThat(matches, equalTo(Collections.emptyList()));
    }

    @Test
    public void returnsNoMatchesWhenPatternIsNull() {
        List<Integer> matches = matcher.match("panamabananas", null);

        assertThat(matches, equalTo(Collections.emptyList()));
    }

    @Test
    public void returnsNoMatchesWhenTextIsNull() {
        List<Integer> matches = matcher.match(null, "ana");

        assertThat(matches, equalTo(Collections.emptyList()));
    }
}
