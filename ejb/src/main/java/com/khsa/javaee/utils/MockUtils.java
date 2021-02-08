package com.khsa.javaee.utils;

import com.khsa.javaee.model.Address;
import com.khsa.javaee.model.Contact;

import java.util.*;

public final class MockUtils {
    private MockUtils() {
    }

    public static List<String> SAMPLE_FIRST_NAMES = Arrays.asList("Maxim", "Petr", "Kiril", "Andrey", "Alisa");
    public static List<String> SAMPLE_LAST_NAMES = Arrays.asList("Maximov", "Petrov", "Tereshov", "Leshov", "Smith");
    public static List<String> SAMPLE_MIDDLE_NAMES = Arrays.asList("Ivanovich", "Petrovich", "Sergeevich", "Alexandrovich");

    public static List<String> SAMPLE_CITY = Arrays.asList("Moscow", "Saint-Petersburg", "Kazan", "Kaliningrad", "Voronezh");
    public static List<String> SAMPLE_STREET = Arrays.asList("Lenina", "Moskovskaya", "Frunze", "Dzerzhinskaya", "Polevaya");


    public static String randomId() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static <T> T randomOf(List<T> list) {
        return list.get(new SplittableRandom().nextInt(list.size()));
    }

    public static Set<Address> filterRandomAddressList(List<Address> addresses, int maxCount) {
        Set<Address> randomAddresses = new HashSet<>();
        int count = new SplittableRandom().nextInt(1, maxCount);
        while (randomAddresses.size() < count) {
            randomAddresses.add(randomOf(addresses));
        }
        return randomAddresses;
    }

    public static Contact generateRandomContact(Set<Address> addresses) {

        return Contact.builder()
                .firstName(randomOf(SAMPLE_FIRST_NAMES))
                .lastName(randomOf(SAMPLE_LAST_NAMES))
                .middleName(randomOf(SAMPLE_MIDDLE_NAMES))
                .phoneNumber(new SplittableRandom().nextBoolean() ? randomPhoneNumber() : null)
                .addresses(addresses)
                .build();
    }

    private static String randomPhoneNumber() {
        return String.valueOf(new SplittableRandom().nextInt(100000, 1000000));
    }

    public static Address generateRandomAddress() {
        return Address.builder()
                .city(randomOf(SAMPLE_CITY))
                .postIndex(new SplittableRandom().nextInt(999999))
                .street(randomOf(SAMPLE_STREET))
                .house(String.valueOf(new SplittableRandom().nextInt(100)))
                .apartment(String.valueOf(new SplittableRandom().nextInt(100))).build();
    }
}
