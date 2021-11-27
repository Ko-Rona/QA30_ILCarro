package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    private Iterator<Object[]> loginDto() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"rona666@mail.ru", "KoronA10!"});
        list.add(new Object[]{"rona666@mail.ru", "KoronA10!"});
        list.add(new Object[]{"rona666@mail.ru", "KoronA10!"});

        return list.iterator();
    }

    @DataProvider
    private Iterator<Object[]> loginModelDto() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("rona666@mail.ru").withPassword("KoronA10!")});
        list.add(new Object[]{new User().withEmail("rona666@mail.ru").withPassword("KoronA10!")});
        return list.iterator();
    }

    @DataProvider
    private Iterator<Object[]> registrationModelDto() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("rona" + i + "@mail.ru").withPassword("12345Asf!" + i)
                .withLastName("Hopp").withName("Lis")});
        list.add(new Object[]{new User().withEmail("rona" + i + "@mail.ru").withPassword("12345Asf!" + i)
                .withLastName("Hopp").withName("Lis")});
        list.add(new Object[]{new User().withEmail("rona" + i + "@mail.ru").withPassword("12345Asf!" + i)
                .withLastName("Hopp").withName("Lis")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =
                new BufferedReader
                        (new FileReader
                                (new File
                                        ("E:\\QA30\\QA30_ILCarro\\src\\test\\resources\\data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .withName(split[0])
                    .withLastName(split[1])
                    .withEmail(split[2])
                    .withPassword(split[3])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationCSV2() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\data.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .withName(split[0])
                    .withLastName(split[1])
                    .withEmail(split[2])
                    .withPassword(split[3])});

            line= reader.readLine();
        }
        return list.iterator();
    }

}
