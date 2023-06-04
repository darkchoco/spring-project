package darkchoco.reactspringbootmybatis;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductDAO productDAO;

    private final ProductMapper productMapper;

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index", "", "");
    }

    @RequestMapping("/list")
    public List<Map<String, Object>> list(@RequestParam(defaultValue = "") String product_name) {
        return productDAO.list(product_name);
    }

    @PostMapping("/insert")
    public int insert(@RequestParam("product_name") String product_name,
                      @RequestParam("description") String description,
                      @RequestParam("price") BigDecimal price,
                      @RequestParam(required = false) MultipartFile img,
                      HttpServletRequest req) {
        return productMapper.insert(product_name, description, price, getFileNameFromReq(img, req));
    }

    @GetMapping("/detail/{product_code}")
    public Product detail(@PathVariable Integer product_code) {
        return productMapper.getByProductCode(product_code);
    }

    @PostMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public int update(@RequestPart("product") Product product,
                      @RequestPart(name = "img", required = false) MultipartFile img,
                      HttpServletRequest req) {
        product.setFilename(getFileNameFromReq(img, req));

        return productMapper.update(product);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam("product_code") Integer product_code,
                      HttpServletRequest req) {
        String filename = productDAO.filename(product_code);

        if (filename != null && !filename.equals("-")) {
            ServletContext application = req.getSession().getServletContext();
            String path = application.getRealPath("/static/images/");
            File file = new File(path + filename);
            if (file.exists()) {
                if (file.delete())
                    System.out.println("File is deleted.");
                else
                    System.out.println("Sorry, unable to delete the file.");
            }
        }

        return productMapper.delete(product_code);
    }

    private String getFileNameFromReq(MultipartFile img, HttpServletRequest req) {
        String filename = "-";

        if (img != null && !img.isEmpty()) {
            filename = img.getOriginalFilename();

            try {
                // application 객체 가져오기. 이 application은 웹서버의 정보를 조회할 수 있는 객체이다.
                ServletContext application = req.getSession().getServletContext();
                String path = application.getRealPath("/static/images/");
                img.transferTo(new File(path + filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return filename;
    }

}
