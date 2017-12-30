package com.flatstack.android.utils;

import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.functions.Func1;

public class ListsTest {

    @Test
    public void groupBy() {
        List<TestClass> testList = new ArrayList<>();
        testList.add(new TestClass("1", "kokoko"));
        testList.add(new TestClass("3", "kekeke"));
        testList.add(new TestClass("5", "kikiki"));
        testList.add(new TestClass("7", "kakaka"));
        testList.add(new TestClass("9", "kykyky"));

        Pair<List<TestClass>, List<TestClass>> listListPair =
                Lists.groupBy(testList, testClass -> testClass.id.compareTo("5") < 0);

        Assert.assertEquals(2, listListPair.first.size());
        Assert.assertEquals(3, listListPair.second.size());
        Assert.assertEquals("kokoko", listListPair.first.get(0).anotherField);
        Assert.assertEquals("kekeke", listListPair.first.get(1).anotherField);

        Assert.assertEquals("kikiki", listListPair.second.get(0).anotherField);
        Assert.assertEquals("kakaka", listListPair.second.get(1).anotherField);
        Assert.assertEquals("kykyky", listListPair.second.get(2).anotherField);
    }

    @Test
    public void map() throws Exception {
        // Arrange
        String expectedCat1Name = "loli";
        String expectedCat1Age = "1";

        String expectedCat2Name = "Badu";
        String expectedCat2Age = "5";

        List<Cat> expectedInput = Arrays.asList(
                new Cat(expectedCat1Name, expectedCat1Age),
                new Cat(expectedCat2Name, expectedCat2Age)
        );
        List<Dog> expectedOutput = Arrays.asList(
                new Dog(expectedCat1Name, expectedCat1Age),
                new Dog(expectedCat2Name, expectedCat2Age)
        );

        Func1<Cat, Dog> expectedMapper = cat -> new Dog(cat.name, cat.age);

        // Act
        List<Dog> actualOutput = Lists.map(expectedInput, expectedMapper);

        // Assert
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    private static class TestClass {

        private String id;
        private String anotherField;

        TestClass(String id, String anotherField) {
            this.id = id;
            this.anotherField = anotherField;
        }
    }

    private static class Cat {
        String name;
        String age;

        public Cat(String name, String age) {
            this.name = name;
            this.age = age;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cat cat = (Cat) o;

            if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
            return age != null ? age.equals(cat.age) : cat.age == null;
        }

        @Override public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (age != null ? age.hashCode() : 0);
            return result;
        }
    }

    private static class Dog {
        String name;
        String age;

        public Dog(String name, String age) {
            this.name = name;
            this.age = age;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Dog dog = (Dog) o;

            if (name != null ? !name.equals(dog.name) : dog.name != null) return false;
            return age != null ? age.equals(dog.age) : dog.age == null;
        }

        @Override public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (age != null ? age.hashCode() : 0);
            return result;
        }
    }
}