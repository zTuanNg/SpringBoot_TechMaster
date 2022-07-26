package vn.techmaster.bookstore.repository;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.util.ResourceUtils;
import vn.techmaster.bookstore.model.Book;

public class BookDao extends Dao<Book> {

  public BookDao(String csvFile) {
    this.readCSV(csvFile);
  }

  @Override
  public void readCSV(String csvFile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + csvFile);
      CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('|'); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Book.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
      Reader reader = new FileReader(file);
      MappingIterator<Book> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
        Book book = mi.next();
        this.add(book);
      }
    } catch (IOException e) {
      System.out.println(e);   
    }
  }

  @Override
  public List<Book> getAll() {
    return collections;
  }

  @Override
  public Optional<Book> get(int id) {
    return collections.stream().filter(u -> u.getId() == id).findFirst();
  }

  @Override
  public void add(Book book) {
    //Cơ chế tự tăng
    int id;
    if (collections.isEmpty()) {
      id = 1;
    } else {
      Book lastBook = collections.get(collections.size() - 1);
      id = lastBook.getId() + 1;      
    }
    book.setId(id);
    collections.add(book);
  }

  @Override
  public void update(Book book) {
    get(book.getId()).ifPresent(existbook -> {
      existbook.setTitle(book.getTitle());
      existbook.setDescription(book.getDescription());
    });
  }
  

  @Override
  public void deleteByID(int id) {
    get(id).ifPresent(existbook -> collections.remove(existbook));
  }

  @Override
  public void delete(Book book) {
    deleteByID(book.getId());
  }

  @Override
  public List<Book> searchByKeyword(String keyword) {
    //Tham khảo chi tiết ở đây nhé. Đây là Lambda Expression có từ Java 8.
    //https://www.baeldung.com/java-stream-filter-lambda  
    return collections
    .stream()
    .filter(book -> book.matchWithKeyword(keyword))
    .collect(Collectors.toList());
  }
}