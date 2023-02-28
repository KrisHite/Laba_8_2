import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, EOFException {
        File fileForRead = null;
        File fileForWrite = null;
        try{
            //Создаю 2 новых файла
            fileForRead = new File("fileForRead.txt");
            fileForWrite = new File("fileForWrite.txt");
            fileForRead.createNewFile();
            System.out.printf("" + fileForRead.getAbsolutePath());
            fileForWrite.createNewFile();
            System.out.printf("\n" + fileForWrite.getAbsolutePath());
            //Создат байтовый поток для записи файла 1
            DataOutputStream dOut = new DataOutputStream(new FileOutputStream(fileForRead));
            System.out.print("\nСодержимое первого файла");
            String strOne = "This if first string";
            String strTwo = "This if second string";
            //Записал в файл 2 строки UTF_8 и 5 чисел типа double
            dOut.writeUTF(strOne);
            System.out.print("\n" + strOne);
            dOut.writeUTF(strTwo);
            System.out.print("\n" + strTwo);
            for(int i = -2;i < 3;i++){
                dOut.writeDouble(i);
                System.out.print("\n" + (double)i);
            }
            //Открываю поток чтения из первого файла
            DataInputStream dIn = new DataInputStream(new FileInputStream(fileForRead));
            //Открываю поток для записи второго файла
            DataOutputStream dOutSecond = new DataOutputStream(new FileOutputStream(fileForWrite));
            System.out.printf("\n\n\nСодержимое второго файла");
            int count = 2;//Номер строки который необходимо переписать
            String buff = null;
            for(int i = 1;i <= count;i++){
                buff = dIn.readUTF();
            }
            dOutSecond.writeUTF(buff);
            System.out.printf("\n" + buff);
            Double doubleBuff;
            for(int i = 0; i<5; i++){
                doubleBuff = dIn.readDouble();
                if(doubleBuff > 0){
                    dOutSecond.writeDouble(doubleBuff);
                    System.out.printf("\n" + doubleBuff);
                }
            }
            //Закрываю потоки
            dOut.flush();
            dOut.close();
            dOutSecond.flush();
            dOutSecond.close();
            dIn.close();
        }catch (IOException e){
            System.out.printf(" " + e);

        }


    }
}
//        Задание 2. Создать проект, позволяющий из одного, предварительно
//        созданного программными средствами файла, переписать данные,
//        соответствующие условию - в исходном файле содержится две строки в
//        формате UTF-8 и 5 чисел типа double. В результирующий файл переписать
//        вторую строку и положительные числа.