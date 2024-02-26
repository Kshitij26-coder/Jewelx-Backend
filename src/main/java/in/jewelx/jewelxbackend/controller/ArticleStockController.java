package in.jewelx.jewelxbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;
import in.jewelx.jewelxbackend.service.impl.ArticleStockService;

@RestController
@RequestMapping("/article-stock")
public class ArticleStockController {

	@Autowired
	private ArticleStockService articleStockService;

	@PostMapping
	public ResponseEntity<String> addArticleStock(@RequestBody ArticleStockDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(articleStockService.addArticleStock(dto));
	}

	@GetMapping
	public ResponseEntity<?> getAllArticleStocks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Long subsidiary, @RequestParam Long brand,
			@RequestParam String role) {
		return ResponseEntity.ok(articleStockService.getAllArticleStocks(page, size, subsidiary, brand, role));
	}

	@GetMapping("/all/{id}")
	public ResponseEntity<?> getAllArticles(@PathVariable Long id) {
		return ResponseEntity.ok(articleStockService.getAllArticlesby(id));
	}

	@DeleteMapping("/{articleStockId}")
	public ResponseEntity<String> deleteArticleStockById(@PathVariable("articleStockId") Long id) {
		return ResponseEntity.ok(articleStockService.deleteByArticleStockId(id));
	}

	@PutMapping("/{articleStockId}")
	public ResponseEntity<String> updateArticleStockById(@RequestBody ArticleStockDto dto,
			@PathVariable("articleStockId") Long id) {
		return ResponseEntity.ok(articleStockService.updateArticleStockById(id, dto));
	}

	@GetMapping("/{articleStockId}")
	public ResponseEntity<ArticleStockRespDto> getArticleStockById(@PathVariable("articleStockId") Long id) {
		ArticleStockRespDto foundArticleStock = articleStockService.getArticleStockById(id);
		return ResponseEntity.ok(foundArticleStock);
	}

//	@GetMapping("/barcode/{id}")
//	public void barcode(@PathVariable("id")String id, HttpServletResponse response) throws Exception{
//		
//		response.setContentType("image/png");
//		OutputStream outputStream = response.getOutputStream();
//		outputStream.write(BarcodeGenerator.getBarCodeImage(id, 500, 300));
//		outputStream.flush();
//		outputStream.close();
//	}
}
