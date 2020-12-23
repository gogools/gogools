package com.gogools.chop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.gogools.chop.domain.EventRegistry;
import com.gogools.chop.domain.Product;
import com.gogools.chop.dto.EventRegistryDTO;
import com.gogools.chop.dto.ProductDTO;
import com.gogools.chop.mapper.EventRegistryMapper;
import com.gogools.chop.mapper.ProductMapper;
import com.gogools.chop.services.EventRegistryService;
import com.gogools.chop.services.ProductService;
import com.gogools.enums.Emj;
import com.gogools.rest.RespEntityCreator;
import com.gogools.utils.file.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chop")
public class BusinessCtrl {
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private EventRegistryService eventService;
	
	@Autowired
	private ProductMapper pMapper;
	
	@Autowired
	private EventRegistryMapper erMapper;
	
	/**
	 * Test endpoint
	 * @return
	 */
	@PostMapping("/test")
    public ResponseEntity<?> postTest() {
		
		log.info(Emj.INFO + " TEST");
		
        return new RespEntityCreator<String>("This is a test").ok();
    }
	
	
	/**
	 * Test endpoint
	 * @return
	 */
	@GetMapping("/test")
    public ResponseEntity<?> getTest() {
		
		log.info(Emj.INFO + " TEST");
		
        return new RespEntityCreator<String>("This is a test").ok();
    }
	
	
	/**
	 * 
	 * @param the product to be add
	 * @return a product with a db id
	 */
	@PostMapping("/product")	
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO product) {
		
		log.info(Emj.INFO + " Trying to add product: {}", product);
		
		if (product == null) {

			return new RespEntityCreator<>("Product is null").bad();
		}
		
		if( prodService.getProductByCode(product.getCode()) != null ) {
			
			return new RespEntityCreator<>("Product code already exist", product).bad();
		}
		
		Product p = prodService.saveProduct( pMapper.productDTO2Product(product) );
		
		return new RespEntityCreator<>("Product added", pMapper.product2ProductDTO(p) ).created();
	}
	

	/**
	 * 
	 * @param the event to be add
	 * @return a event with a db id
	 */
	@PostMapping("/event")	
	public ResponseEntity<?> addEvent(@Valid @RequestBody EventRegistryDTO event) {
		
		log.info(Emj.INFO + " Trying to add event registry: {}", event);
		
		if (event == null) {

			return new RespEntityCreator<>("Event registry is null").bad();
		}
		
		Long eventCode = eventService.getCount() + 1;
		event.setEventCode( eventCode.toString() );
		
		EventRegistry er = eventService.saveEventRegistry( erMapper.eventrDTO2eventr(event) );
		
		return new RespEntityCreator<>("Event registry added", erMapper.eventr2eventrDTO(er) ).created();
	}
	
	
	/**
	 * 
	 * @param productId product id to update
	 * @param product product data to update
	 * @return the product updated with db id
	 * 
	 * Note: this method removes id from product before trying to update it, code takes in consideration only productId
	 */
	@PutMapping("/product")	
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO product) {
		
		log.info(Emj.INFO + " Trying to update product: {}", product);
		
		if (product == null || product.getId() == null || product.getId().isEmpty()) {

			return new RespEntityCreator<>("Product is null or id is missing").bad();
		}
		
		Product p = pMapper.productDTO2Product(product);
		
		if( prodService.updateProduct(p) == null ) {
			
			return new RespEntityCreator<>("Product ID doesn't exist", product).bad();
		}
		
		product = pMapper.product2ProductDTO(p);
		
		return new RespEntityCreator<>("Product updated", product).ok();
	}
	
	
	/**
	 * 
	 * @return all the products
	 */
	@GetMapping("/products")
	public ResponseEntity<?> getProducts( ) {
		
		log.info(Emj.INFO + " Fetching all products");
		
		List<Product> products = prodService.getAllProducts();
		
		log.info(Emj.INFO + " Products fetched: {}", products.size());
		
		return new RespEntityCreator<>("# of products (" + products.size()+ ")", products.stream().map(x -> pMapper.product2ProductDTO( x ) )).ok();
	}
	
	
	/**
	 * 
	 * @param code, the product code
	 * @return product code if found
	 */
	@GetMapping("/product/code/{code}")
	public ResponseEntity<?> getProductByCode( @PathVariable("code") @NotBlank String code ) {
		
		log.info(Emj.INFO + " Fetching product with code: {}", code);
		
		Product p = prodService.getProductByCode(code);
		
		return p != null 
				? 
				new RespEntityCreator<>("Product:", pMapper.product2ProductDTO(p) ).ok()
				:
				new RespEntityCreator<>("Product not found with code: " + code, null).notFound();
	}
	
	
	/**
	 * 
	 * @param code, the product code
	 * @return product code if found
	 */
	@GetMapping("/product/id/{id}")
	public ResponseEntity<?> getProductById( @PathVariable("id") @NotBlank String id ) {
		
		log.info(Emj.INFO + " Fetching product with ID: {}", id);
		
		Product p = prodService.getProductById(id);
		
		return p != null 
				? 
				new RespEntityCreator<>("Product:", pMapper.product2ProductDTO(p)).ok()
				:
				new RespEntityCreator<>("Product not found with id: " + id, null).notFound();
	}
	

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct( @PathVariable("id") @NotBlank String id ) {
		
		log.info(Emj.INFO + " Fetching product with ID: {} to delete it", id);
		
		Product p = prodService.getProductById(id);
		
		p.setActive(false);
		p = prodService.updateProduct(p);
		
		return p != null 
				? 
				new RespEntityCreator<>("Product:", pMapper.product2ProductDTO(p)).ok()
				:
				new RespEntityCreator<>("Product not found with id: " + id, null).notFound(); 
	}
	
	
	/**
	 * 
	 * @param excel, Chop Chop Shop catalog
	 * @return a list of Products with a message inside RestResponse
	 */
	@PostMapping("/bulkCatalog")
	public ResponseEntity<?> createCatalog(@RequestParam("excel") MultipartFile excel) {

		log.info(Emj.INFO + " uploaded file: [{}], file is empty? {}", excel.getOriginalFilename(), excel.isEmpty());

		if (excel == null || excel.isEmpty()) {

			return new RespEntityCreator<>("File is empty or null").bad();
		}
		
		List<Product> products;
		
		try {
			
			products = fetchProductsFromFile( excel.getInputStream() );
			
		} catch (IOException e) {
			
			log.info(Emj.ERR +"== error: {}", e.getMessage());
			return new RespEntityCreator<>(e.getMessage()).err();
		}
		
		if( products.isEmpty() ) return new RespEntityCreator<>("Nothing was updated").bad();
		
		if( prodService.countAll() == 0 ) {
		
			products = prodService.saveProducts(products);
			
		} else {
			
			List<Product> aux = new ArrayList<>();
			
			for( Product p : products ) {
				
				Product dbProduct = prodService.getProductByCode( p.getCode() );
				
				if( dbProduct == null ) {
					
					aux.add( prodService.saveProduct( p ) );
					
				} else {
					
					p.setId(dbProduct.getId());
					aux.add( prodService.updateProduct( p ) );
					
				}
			}
		}

		log.info(Emj.OK + " Products saved: [{}].", products.size());
		
		return new RespEntityCreator<>("Catalog created, # of products (" + products.size() + ")", 
										products
											.stream()
											.map(x -> pMapper.product2ProductDTO(x))
											.collect(Collectors.toList()))
									  .ok();
	}
	
	/**
	 * 
	 * @param is, the excel file to read
	 * @return a list of products created from excel file
	 * @throws IOException
	 * 
	 * NOTES: code validates that none columns has null value and the number of columns matches the number of
	 * 		  Product attributes minus 1 in order to avoid ID attribute that MongoDB needs 
	 * 		  Code also avoid reading row #0 of each sheet (headers)
	 */
	private List<Product> fetchProductsFromFile( InputStream is) throws IOException {
		
		try {

			List<Product> results = new ArrayList<>();
			Workbook workbook = new XSSFWorkbook( is );

			int sIndex = 0;
			
			for( Sheet sheet : workbook) {

				log.info(Emj.INFO + " Reading sheet name:{} {}/{}, rows:{}", sheet.getSheetName(), ++sIndex, workbook.getNumberOfSheets(), sheet.getPhysicalNumberOfRows());				
				
				for (Row row : sheet) {
				
					log.info(Emj.INFO + " Row:{}, NullCols: {}, Phy#Cells:{}, ProdFields: {}", row.getRowNum(), ExcelUtil.hasNullInAnyColumns(row), row.getPhysicalNumberOfCells(), Product.class.getDeclaredFields().length);	
					if( row.getRowNum() == 0 || ExcelUtil.hasNullInAnyColumns(row) || row.getPhysicalNumberOfCells() != Product.class.getDeclaredFields().length) continue;
					if( row.getCell(0).getStringCellValue().trim().isEmpty() || 
						row.getCell(1).getStringCellValue().trim().isEmpty() || 
						row.getCell(2).getStringCellValue().trim().isEmpty() ||
						row.getCell(7).getStringCellValue().trim().isEmpty()) continue;
							
					Product p = new Product();
					
					p.setBrandId(	row.getCell(0).getStringCellValue().trim().toLowerCase() ); //TODO
					p.setCode( 		row.getCell(1).getStringCellValue().trim() );
					p.setName(      row.getCell(2).getStringCellValue().trim() );
					p.setDescrip( 	row.getCell(3).getStringCellValue().trim() );
					p.setImgName( 	row.getCell(4).getStringCellValue().trim() );
					p.setPrice(		row.getCell(5).getNumericCellValue());
					p.setQty(  (int)row.getCell(6).getNumericCellValue());
					p.setType( 		row.getCell(7).getStringCellValue().trim().toLowerCase() );					

					log.info(Emj.INFO + " Product: {}", p);
					
					results.add(p);
				}
				
			}

			workbook.close();
			
			return results;

		} catch (IOException e) {

			e.printStackTrace();
			log.info(Emj.ERR +e.getMessage());
			throw e;
		}
	}
}
