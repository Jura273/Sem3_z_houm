//    Напишите приложение, которое будет запрашивать у
//    пользователя следующие данные в произвольном порядке,
//    разделенные пробелом: Фамилия Имя Отчество, дата_рождения,
//    номер_телефона, пол
//    Форматы данных:фамилия, имя, отчество - строки дата_рождения
//    - строка формата dd.mm.yyyy
//     номер_телефона - целое беззнаковое число без форматирования
//     пол - символ латиницей f или m.
//     Критерии:
//      Приложение должно проверить введенные данные по количеству.
//     Если количество не совпадает с требуемым,
//     вернуть код ошибки, обработать его и показать пользователю сообщение,
//     что он ввел меньше и больше
//      данных, чем требуется.
//       Приложение должно попытаться распарсить полученные значения
//      и выделить из них требуемые параметры.
//      Если форматы данных не совпадают, нужно бросить исключение,
//      соответствующее типу проблемы. Можно
//      использовать встроенные типы java и создать свои.
//      Исключение должно быть корректно обработано,
//      пользователю выведено сообщение с информацией, что именно неверно.
//         Если всё введено и обработано верно, должен создаться файл
//        с названием, равным фамилии, в него в одну
//        строку должны записаться полученные данные, вида.

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные пользователя в следующем формате:" +
                " Фамилия Имя Отчество датаРождения НомерТелефона Пол");

        String input = scanner.nextLine();

        // Разделение введенных данных по пробелам

        String[] userData = input.split(" ");

        // Проверка количества данных

        if (userData.length != 6) {
            if (userData.length < 6) {
                System.out.println("Вы ввели меньше данных, чем требуется");
            } else {
                System.out.println("Вы ввели больше данных, чем требуется");
            }
            return;
        }

        try {

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String dateOfBirth = userData[3];
            String phoneNumber = userData[4];
            char gender = userData[5].charAt(0);


            // Проверка формата данных

            boolean isValid = true;

            if (!dateOfBirth.matches("\\d{2}.\\d{2}.\\d{4}")) {
                System.out.println("Дата рождения должна быть указана в формате dd.mm.yyyy");
                isValid = false;
            }
            if (gender != 'f' && gender != 'm') {
                System.out.println("Пол должен быть указан символом 'f' или 'm'.");
                isValid = false;
            }
            if (!isValid) {
                return;
            }

            String fileName = Arrays.toString(new String[]{lastName}) + ".txt";

            FileWriter fileWriter = new FileWriter(fileName, true);

            // открываем файл на запись (true - дописать в файл)

            fileWriter.write(Arrays.toString(new String[]{lastName}) + " " + Arrays.toString(new String[]{firstName}) + " " + Arrays.toString(new String[]{middleName}) + " " + Arrays.toString(new String[]{dateOfBirth}) + " " + Arrays.toString(new String[]{phoneNumber}) + " " + Arrays.toString(new char[]{gender}));

            //закрываем соединение с файлом

            fileWriter.close();

            System.out.println("Данные успешно записаны ив файл !");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
