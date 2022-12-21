package com.example.thithua1.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.recyclerview.Table;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Question.db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String CATEGORIES_TABLE = "CREATE TABLE " +
                Table.CategoriesTable.TABLE_NAME + "( " +
                Table.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String QUESTIONS_TABLE = "CREATE TABLE " +
                Table.QuestionTable.TABLE_NAME + " ( " +
                Table.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.QuestionTable.COLUMN_NAME + " TEXT, " +
                Table.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                Table.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                Table.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                Table.QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                Table.QuestionTable.COLUMN_ANSWER + " INTEGER, " +
                Table.QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + Table.QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                Table.CategoriesTable.TABLE_NAME + "(" + Table.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(CATEGORIES_TABLE);
        db.execSQL(QUESTIONS_TABLE);

        createCategories();
        createQuestion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table.CategoriesTable.COLUMN_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void createCategories(){
        Category cat1 = new Category("Lí thuyết");
        Category cat2 = new Category("Liệt");
        Category cat3 = new Category("Biển Báo");
        Category cat4 = new Category("Tình huống");
        insertCategories(cat1);
        insertCategories(cat2);
        insertCategories(cat3);
        insertCategories(cat4);
    }

    private void insertCategories(Category cat){
        ContentValues values = new ContentValues();
        values.put(Table.CategoriesTable.COLUMN_NAME, cat.getName());
        db.insert(Table.CategoriesTable.TABLE_NAME,null,values);
    }

    private void insertQuestions(Question question){
        ContentValues values = new ContentValues();

        values.put(Table.QuestionTable.COLUMN_NAME,question.getQuestion());
        values.put(Table.QuestionTable.COLUMN_OPTION1,question.getOption1());
        values.put(Table.QuestionTable.COLUMN_OPTION2,question.getOption2());
        values.put(Table.QuestionTable.COLUMN_OPTION3,question.getOption3());
        values.put(Table.QuestionTable.COLUMN_ANSWER,question.getAnswer());
        values.put(Table.QuestionTable.COLUMN_CATEGORY_ID,question.getCategoryID());
        db.insert(Table.QuestionTable.TABLE_NAME,null,values);
    }

    private void createQuestion(){
        Question q1 = new Question("Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì?",
                "A. Phần mặt đường và lề đường.",
                "B. Phần đường xe chạy.",
                "C. Phần đường xe cơ giới.", 2, 1, 0);
        insertQuestions(q1);
        Question q2 = new Question("“Làn đường” là gì?",
                "A. Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.",
                "B. Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.",
                "C. Là đường cho xe ô tô chạy, dừng, đỗ an toàn.", 2, 1,0);
        insertQuestions(q2);
        Question q3 = new Question("Trong các khái niệm dưới đây, “dải phân cách” được hiểu như thế nào là đúng?",
                "A. Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép.", "B. Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông.", "C. Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ.", 3, 1,0);
        insertQuestions(q3);

        Question q4 = new Question("“Dải phân cách” trên đường bộ gồm những loại nào?",
                "A. Dải phân cách gồm loại cố định và loại di động.", "B. Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm.", "C. Dải phân cách gồm giá long môn và biển báo hiệu đường bộ.", 1, 1,0);
        insertQuestions(q4);

        Question q5 = new Question("Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
                "A. Là người điều khiển xe cơ giới.", "B. Là người điều khiển xe thô sơ.", "C. Là người điều khiển xe có súc vật kéo.", 1, 1,0);
        insertQuestions(q5);

        Question q6 = new Question("Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?",
                "A. Đường không ưu tiên.", "B. Đường tỉnh lộ.", "C. Đường ưu tiên.", 3, 1,0);
        insertQuestions(q6);

        Question q7 = new Question("Khái niệm “phương tiện giao thông thô sơ đường bộ” được hiểu thế nào là đúng?",
                "A. Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự.", "B. Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.", "C. Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo.", 1, 1,0);
        insertQuestions(q7);

        Question q8 = new Question("Phương tiện tham gia giao thông đường bộ” gồm những loại nào?",
                "A. Phương tiện giao thông cơ giới đường bộ.", "B. Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng.", "C. Cả ý 1 và ý 2.", 3, 1,0);
        insertQuestions(q8);

        Question q9 = new Question("Người điều khiển phương tiện tham gia giao thông đường bộ” gồm những đối tượng nào dưới đây?",
                "A. Người điều khiển xe cơ giới, người điều khiển xe thô sơ.", "B. Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ.", "C. Cả ý 1 và ý 2.", 3, 1,0);
        insertQuestions(q9);

        Question q10 = new Question("Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
                "A. Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ.", "B. Người điều khiển, dẫn dắt súc vật; người đi bộ trên đường bộ.", "C. Cả ý 1 và ý 2.", 3, 1,0);
        insertQuestions(q10);

        Question q11 = new Question("Khái niệm “người điều khiển giao thông” được hiểu như thế nào là đúng?",
                "A. Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.", "B. Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.", "C. Là người tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.", 2, 1,0);
        insertQuestions(q11);

        Question q12 = new Question("Trong các khái niệm dưới đây khái niệm “dừng xe” được hiểu như thế nào là đúng?",
                "A. Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.", "B. Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.", "C. Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hoá hoặc hành khách.", 2, 1,0);
        insertQuestions(q12);

        Question q13 = new Question("Cuộc đua xe chỉ được thực hiện khi nào?",
                "A. Diễn ra trên đường phố không có người qua lại.", "B. Được người dân ủng hộ.", "C. Được cơ quan có thẩm quyền cấp phép.", 3, 3,0);
        insertQuestions(q13);

        Question q14 = new Question("Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma tuý có bị nghiêm cấm hay không?",
                "A.  Bị nghiêm cấm.", "B. Không bị nghiêm cấm.", "C. Không bị nghiêm cấm, nếu có chất ma tuý ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.", 1, 3,0);
        insertQuestions(q14);

        Question q15 = new Question("Sử dụng rượu bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?",
                "A. Chỉ bị nhắc nhở.", "B. Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm.", "C. Không bị xử lý hình sự.", 2, 3,0);
        insertQuestions(q15);

        Question q16 = new Question("Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông?",
                "A. Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy.", "B. Người ngồi phía sau người điều khiển xe cơ giới.", "C. Cả ý 1 và ý 2.", 1, 3,0);
        insertQuestions(q16);

        Question q17 = new Question("Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?",
                "A. Bị nghiêm cấm tuỳ từng trường hợp.", "B. Không bị nghiêm cấm", "C. Bị nghiêm cấm.", 3,3,0);
        insertQuestions(q17);

        Question q18 = new Question("Khi lái xe trong khu đô thị và đông dân cư trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?",
                "A. Từ 22 giờ đêm đến 5 giờ sáng.", "B. Từ 5 giờ sáng đến 22 giờ tối.", "C. Từ 23 giờ đêm đến 5 giờ sáng hôm sau.", 2, 1,0);
        insertQuestions(q18);

        Question q19 = new Question("Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?",
                "A. Bất cứ đèn nào miễn là mắt nhìn rõ phía trước.", "B. Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều.", "C. Đèn chiếu gần (đèn cốt).", 3, 1,0);
        insertQuestions(q19);

        Question q20 = new Question("Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?",
                "A. Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp.", "B. Phải được chấp thuận của cơ quan có thẩm quyền.", "C. Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp.", 2, 1,0);
        insertQuestions(q20);

        Question q21 = new Question("Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?",
                "A. Được phép.", "B. Không được phép.", "C. Tùy từng trường hợp.", 2, 3,0);
        insertQuestions(q21);

        Question q22 = new Question("Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
                "A. Không được vượt.", "B. Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.", "C. Được vượt khi đảm bảo an toàn.", 3, 1,0);
        insertQuestions(q22);

        Question q23 = new Question("Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
                "A. Không được vượt.", "B. Được vượt khi đang đi trên cầu.", "C. Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia  giao thông.", 1, 1,0);
        insertQuestions(q23);

        Question q24 = new Question("Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
                "A. Được phép.", "B. Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.", "C. Không được phép.", 3, 3,0);
        insertQuestions(q24);

        Question q25 = new Question("Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?",
                "A. Được phép.", "B. Tuỳ trường hợp.", "C. Không được phép.", 3, 3,0);
        insertQuestions(q25);

        Question q26 = new Question("Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?",
                "A. Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy.", "B. Buông một tay; sử dụng xe để chở người hoặc hàng hoá; để chân chạm xuống đất khi khởi hành.", "C. Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.", 1, 3,0);
        insertQuestions(q26);

        Question q27 = new Question("Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?",
                "A. Được mang, vác tuỳ trường hợp cụ thể.", "B. Không được mang, vác.", "C. Được mang, vác tùy theo sức khỏe của bản thân.", 2, 3,0);
        insertQuestions(q27);

        Question q28 = new Question("Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?",
                "A. Được phép.", "B. Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.", "C. Không được phép.", 3, 3,0);
        insertQuestions(q28);

        Question q29 = new Question("Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không?",
                "A. Được sử dụng.", "B. Không được sử dụng.", "C.  Được sử dụng nếu không có áo mưa.", 2, 3,0);
        insertQuestions(q29);

        Question q30 = new Question("Khi đang lên dốc người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?",
                "A. Chỉ được phép nếu cả hai đội mũ bảo hiểm.", "B. Chỉ được thực hiện trên đường thật vắng.", "C.  Không được phép.", 3, 3,0);
        insertQuestions(q30);

        Question q31 = new Question("",
                "A. 1.", "B. 2", "C.  3", 3, 2,1);
        insertQuestions(q31);

        Question q32 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,2);
        insertQuestions(q32);

        Question q33 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,3);
        insertQuestions(q33);

        Question q34 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,4);
        insertQuestions(q34);

        Question q35 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,5);
        insertQuestions(q35);

        Question q36 = new Question("",
                "A. 1.", "B. 2", "C.  3", 3, 2,6);
        insertQuestions(q36);

        Question q37 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,7);
        insertQuestions(q37);

        Question q38 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,8);
        insertQuestions(q38);

        Question q39 = new Question("",
                "A. 1.", "B. 2", "C.  3", 3, 2,9);
        insertQuestions(q39);

        Question q40 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,10);
        insertQuestions(q40);

        Question q41 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,11);
        insertQuestions(q41);

        Question q42 = new Question("",
                "A. 1.", "B. 2", "C.  3", 3, 2,12);
        insertQuestions(q42);

        Question q43 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,13);
        insertQuestions(q43);

        Question q44 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,14);
        insertQuestions(q44);

        Question q45 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,15);
        insertQuestions(q45);

        Question q46 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,16);
        insertQuestions(q46);

        Question q47 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,17);
        insertQuestions(q47);

        Question q48 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,18);
        insertQuestions(q48);

        Question q49 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,19);
        insertQuestions(q49);

        Question q50 = new Question("",
                "A. 1.", "B. 2", "C.  3", 3, 2,20);
        insertQuestions(q50);

        Question q51 = new Question("",
                "A. 1.", "B. 2", "C.  3", 1, 2,21);
        insertQuestions(q51);

        Question q52 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,22);
        insertQuestions(q52);

        Question q53 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,23);
        insertQuestions(q53);

        Question q54 = new Question("",
                "A. 1.", "B. 2", "C.  3", 2, 2,24);
        insertQuestions(q54);


    }

    @SuppressLint("Range")
    public List<Category> getDataCategories(){
        List<Category> categoryList = new ArrayList<>();
        db =  getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Table.CategoriesTable.TABLE_NAME,null);
        if (c.moveToFirst()){
            do{
                Category cat = new Category();
                cat.setId(c.getInt(c.getColumnIndex(Table.CategoriesTable._ID)));
                cat.setName(c.getString(c.getColumnIndex(Table.CategoriesTable.COLUMN_NAME)));
                categoryList.add(cat);
            }
            while(c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    @SuppressLint("Range")
    public ArrayList<Question> getQuestions(int categoryID){
        ArrayList<Question> questionArrayList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = Table.QuestionTable.COLUMN_CATEGORY_ID + "= ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};
        Cursor c = db.query(Table.QuestionTable.TABLE_NAME,null,selection,selectionArgs,null,null,null);
        if (c.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(Table.QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(Table.QuestionTable.COLUMN_NAME)));
                question.setOption1(c.getString(c.getColumnIndex(Table.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Table.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Table.QuestionTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Table.QuestionTable.COLUMN_ANSWER)));
                question.setCategoryID(c.getInt(c.getColumnIndex(Table.QuestionTable.COLUMN_CATEGORY_ID)));
                questionArrayList.add(question);


            }while (c.moveToNext());
        }
        c.close();
        return questionArrayList;
    }
}
