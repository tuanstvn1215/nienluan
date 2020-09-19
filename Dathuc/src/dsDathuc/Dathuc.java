package dsDathuc;

public class Dathuc {
   private Donthuc[] dsDonthuc;
   int length;

   public Dathuc() {
      length = 0;
      dsDonthuc = new Donthuc[0];
   }

   public Dathuc(Dathuc dathuc) {
      this.dsDonthuc = new Donthuc[dathuc.length];
      for (int i = 0; i < dathuc.length; i++) {
         this.dsDonthuc[i] = new Donthuc(dathuc.layDonthuc(i));
         length = dathuc.length;
      }
   }

   public Dathuc(Donthuc[] ds) {
      this.dsDonthuc = ds;
   }

   public Dathuc(String string) {
      Donthuc donthuc;
      string = string.toLowerCase();
      string = string.replace("x^0", "");
      string = string.replace("--", "+");
      string = string.replace("++", "+");
      string = string.replace("+-", "-");
      string = string.replace("+", ",");
      string = string.replace("-", ",-");
      string = string.replace("x^", "x");
      String[] arrStr = string.split(",");
      for (int i = 0; i < arrStr.length; i++) {
         donthuc = new Donthuc(arrStr[i]);
         this.themDonthuc(donthuc);
      }

   }

   public void themDonthuc(Donthuc donthuc) {

      Donthuc[] dsDonthuc = new Donthuc[this.length + 1];
      for (int i = 0; i < this.length; i++)
         dsDonthuc[i] = this.dsDonthuc[i];
      dsDonthuc[this.length] = new Donthuc(donthuc.laySo(), donthuc.layMu());
      this.dsDonthuc = new Donthuc[this.length + 1];
      this.dsDonthuc = dsDonthuc;
      this.length++;
   }

   // lấy ra toàn bộ đơn thức
   public Donthuc[] laydsDonthuc() {
      return dsDonthuc;
   }

   // lấy ra 1 đơn thức
   public Donthuc layDonthuc(int i) {
      return dsDonthuc[i];
   };

   // sửa 1 đơn thức tại vị trí i
   public void suaDonthuc(int i/* vị trí */, float so, int mu) {
      this.dsDonthuc[i] = new Donthuc(so, mu);
   }

   public void suaDonthuc(int i/* vị trí */, Donthuc donthuc) {
      this.dsDonthuc[i] = donthuc;
   }

   // trả về độ dài của đa thức
   public int layDodai() {
      return this.length;
   }

   // trả về 1 da thức đã sắp xếp theo số mũ tăng dần
   public Dathuc sapxepDathuc() {
      Dathuc dathuc = new Dathuc(this);
      for (int i = 0; i < this.length - 1; i++)
         for (int j = i; j < this.length; j++)
            if (dathuc.layDonthuc(j).layMu() < dathuc.layDonthuc(i).layMu())
               dathuc.doichoDonthuc(j, i);
      return dathuc;
   }

   public void doichoDonthuc(int i, int j/* vị trí 2 đơn thức cần đổi chỗ */) {
      Donthuc donthuc = new Donthuc();
      donthuc = this.dsDonthuc[i];
      this.dsDonthuc[i] = this.dsDonthuc[j];
      this.dsDonthuc[j] = donthuc;
   }

   // Trả về 1 đa thức đã thu gọn
   public Dathuc thugonDathuc() {
      Dathuc dathuc1 = new Dathuc(this);
      // sắp xếp lại
      dathuc1 = dathuc1.sapxepDathuc();
      // xử lí thu gọn
      Dathuc dathuc = new Dathuc();
      Donthuc donthuc = new Donthuc();
      int i = 0;
      // vì ô đầu tiên là rỗng nên thêm 1 đơn thức vào
      dathuc.themDonthuc(donthuc);
      for (i = 0; i < dathuc1.layDodai(); i++) {
         if (dathuc1.layDonthuc(i).layMu() > dathuc.layDonthuc(dathuc.layDodai() - 1).layMu()) {
            donthuc = dathuc1.layDonthuc(i);
            dathuc.themDonthuc(donthuc);
         } else {
            donthuc = dathuc.layDonthuc(dathuc.layDodai() - 1).congDonthuc(dathuc1.layDonthuc(i).laySo());
            dathuc.suaDonthuc(dathuc.layDodai() - 1, donthuc);
         }
      }
      for (i = 0; i < dathuc.layDodai(); i++) {
         if (dathuc.layDonthuc(i).laySo() == 0) {
            dathuc = dathuc.xoaDonthuc(i);
         }
      }
      return dathuc;
   }

   public Dathuc xoaDonthuc(int i) {
      Dathuc ketqua = new Dathuc();
      for (int j = 0; j < this.length; j++) {
         if (j == i) {
            continue;
         }
         ketqua.themDonthuc(this.layDonthuc(j));
      }

      return ketqua;
   }

   public Dathuc truDathuc(Dathuc dathuc2) {
      float so = 0;
      int mu = 0;
      Dathuc dathuc1 = this;
      Dathuc ketqua = new Dathuc();
      dathuc2 = dathuc2.thugonDathuc();
      Donthuc donthuc = new Donthuc();
      int i, j;

      for (i = 0; i < dathuc1.layDodai(); i++) {
         donthuc = new Donthuc(dathuc1.layDonthuc(i));
         ketqua.themDonthuc(donthuc);
      }

      for (j = 0; j < dathuc2.layDodai(); j++) {
         so = -dathuc2.layDonthuc(j).laySo();
         mu = dathuc2.layDonthuc(j).layMu();
         donthuc = new Donthuc(so, mu);
         ketqua.themDonthuc(donthuc);
      }
      ketqua = ketqua.thugonDathuc();

      return ketqua;
   }

   public Dathuc nhandDathuc(Dathuc dathuc2) {
      Dathuc ketqua = new Dathuc();
      Donthuc donthuc = new Donthuc();
      for (int i = 0; i < this.layDodai(); i++) {
         for (int j = 0; j < dathuc2.layDodai(); j++) {
            donthuc = this.layDonthuc(i).nhanDonthuc(dathuc2.layDonthuc(j));
            ketqua.themDonthuc(donthuc);
         }
      }
      ketqua = ketqua.thugonDathuc();
      return ketqua;
   }

   // Cộng với đa thức(dathuc ) trả về 1 đa thức đã cộng
   public Dathuc CongDathuc(Dathuc dathuc2) {
      Dathuc dathuc1 = this;
      Dathuc ketqua = new Dathuc();
      dathuc2 = dathuc2.thugonDathuc();
      Donthuc donthuc = new Donthuc();
      int i, j;
      for (i = 0; i < dathuc1.layDodai(); i++) {
         donthuc = new Donthuc(dathuc1.layDonthuc(i));
         ketqua.themDonthuc(donthuc);
      }

      for (j = 0; j < dathuc2.layDodai(); j++) {
         donthuc = new Donthuc(dathuc2.layDonthuc(j));
         ketqua.themDonthuc(donthuc);
      }
      ketqua = ketqua.thugonDathuc();
      return ketqua;
   }

   public Dathuc daohamDathuc() {
      Dathuc ketqua = new Dathuc();
      for (int i = 0; i < this.layDodai(); i++) {
         ketqua.themDonthuc(this.layDonthuc(i).daohamDonthuc());
      }
      for (int i = 0; i < ketqua.layDodai(); i++) {
         ketqua = ketqua.thugonDathuc();
      }
      return ketqua;
   }

   // chuyển đa thức thành chuỗi
   public String toString() {
      String ketqua = new String();
      for (int i = 0; i < this.length; i++) {
         ketqua += this.dsDonthuc[i] + " + ";
      }
      if (ketqua.equals(""))
         return "0";
      ketqua = ketqua.substring(0, ketqua.length() - 2);

      return ketqua;
   }

   public static void main(String[] args) throws Exception {

      Dathuc dathuc = new Dathuc("1");
      Dathuc dt = new Dathuc("x+2+2x5+3x^4");
      dathuc = dathuc.nhandDathuc(dt);
      // dathuc = dathuc.Derivative();
      String asd = dathuc.toString();
      System.out.println(asd);
   }
}
