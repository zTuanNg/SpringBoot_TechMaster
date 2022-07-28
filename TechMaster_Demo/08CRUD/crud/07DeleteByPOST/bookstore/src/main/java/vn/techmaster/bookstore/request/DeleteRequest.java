package vn.techmaster.bookstore.request;

public class DeleteRequest {
  private int bookid;

  public int getId() {
    return bookid;
  }

  @Override
  public String toString() {
    return "Delete the book id: " + bookid;
  }

  public void setId(int id) {
    this.bookid = id;
  }
  
}
