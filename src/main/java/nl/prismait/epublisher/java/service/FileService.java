package nl.prismait.epublisher.java.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import nl.prismait.epublisher.java.dao.IArticleDAO;
import nl.prismait.epublisher.java.dao.IFileDAO;
import nl.prismait.epublisher.java.model.EPublisherFile;
import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.EPublisherMedia;
import nl.prismait.epublisher.java.model.ImageCuts;
import nl.prismait.epublisher.java.model.ImageSize;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Service("fileService")
public class FileService 
{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private IArticleDAO articleDAO;
	
	@Autowired
	private IFileDAO fileDAO;

	
	@Autowired
	private ITenantService tenantService;
	
	
//	public EPublisherMedia getEPublishermedia(String uuid)
//	{
//		return articleDAO.findEPublisherMedia(uuid);
//	}
	
	
	/**
	 * Saves the content on disk based on received info.
	 *  
	 * @param ePublisherMedia
	 * 			an instance of {@link EPublisherMedia} to be saved
	 * @param bytes
	 * 			file in bytes
	 * @param url 
	 * @return an instance of {@link EPublisherMedia}
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 * @throws IOException 
	 */
	public EPublisherMedia saveFile(EPublisherMedia ePublisherMedia, byte[] bytes, String url) throws EpublisherException, IOException 
	{
		writeToFileInFolder(bytes, ePublisherMedia,url);
		return ePublisherMedia;
	}
	
	/**
	 * Copies the file to the given location on disk. It must be a full location with name and extension.
	 * 
	 * @param ePublisherMedia
	 * 				an instance of {@link EPublisherMedia} to be copied
	 * @param destinationPath
	 * 				path where the file needs to be copied
	 * @throws EpublisherException
	 *             if any exception occurs
	 * @throws IOException   if any IO excpetion occurs
	 */
	public void copyFile(EPublisherMedia ePublisherMedia, String destinationPath) throws EpublisherException, IOException 
	{
		File file = null;
		FileInputStream fis = null;
		
		try {
			file = new File(getFileSystemPath(ePublisherMedia,""));
			byte[] bytes = new byte[(int) file.length()];

			fis = new FileInputStream(file);
			
			fis.read(bytes);
			
			copyToLocation(bytes, destinationPath); 
		} catch (IOException ex) {
			logger.info("IO Exception", ex);
		} finally {
			if (fis != null) {
				// Close FileInputStream to avoid memory leaks - SonarQube fix
				fis.close(); 
			}
		}
	}
	
	/**
	 * Deletes the file from the disk and the containing folder.
	 * @param ePublisherMedia
	 * 			an instance of {@link EPublisherMedia} to be deleted 
	 * 
	 */
	public void deleteFile(EPublisherMedia ePublisherMedia)
	{
		File file = new File(getFileSystemPath(ePublisherMedia,""));
		file.delete();
		
		File folder = new File(getFileSystemPathFolder(ePublisherMedia));
		folder.delete();
	}
	
	/**
	 * Returns the image dimensions.
	 * 
	 * @param ePublisherImage
	 * 			an instance of {@link EPublisherImage} to get the image properties
	 * @return a map of width and height
	 * @throws IOException if any IO exception occurs
	 */
	public Map<String, Integer> getImageProperties(EPublisherImage ePublisherImage) throws IOException
	{
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		if(ePublisherImage.getWidth() == 0 || ePublisherImage.getHeight() == 0) 
		{
			File file = new File(getFileSystemPath(ePublisherImage,""));
			BufferedImage bimg = ImageIO.read(file);
			result.put("width", bimg.getWidth());
			result.put("height", bimg.getHeight());
		} else
		{
			result.put("width", ePublisherImage.getWidth());
			result.put("height", ePublisherImage.getHeight());
		}	
		
		return result;
	}
	
	private void writeToFileInFolder(byte[] bytes, EPublisherMedia ePublisherMedia, String url) throws EpublisherException, IOException 
	{
		new File(tenantService.getTenantImageLocation(url) + "/" + ePublisherMedia.getFolderid()).mkdir();
		File file = new File(getFileSystemPath(ePublisherMedia,url));
		
	    writeContentToFile(bytes, file);
	}

	private static void copyToLocation(byte[] bytes, String location) throws EpublisherException, IOException 
	{
		File file = new File(location);
	    writeContentToFile(bytes, file);
	}
	
	private static void writeContentToFile(byte[] bytes, File file) throws EpublisherException, IOException
	{
		FileOutputStream fop = null;
		
		try {
	    	fop = new FileOutputStream(file);
	    	
	        if (!file.exists()) {
	            file.createNewFile();
	        }

	        fop.write(bytes);
	        
	        fop.flush();
	    } 
		catch (IOException e) {
	        throw new EpublisherException(e);
	    } 
		finally {
			// Close FileInputStream to avoid memory leaks - SonarQube fix
			if (fop != null) {
				fop.close();
			}
	    }
	}
	
	public void setImageSize(EPublisherImage imageToReturn) throws IOException {
		Map<String, Integer> imageProperties = getImageProperties(imageToReturn);
		for(Map.Entry<String, Integer> entry : imageProperties.entrySet()) 
		{
			if(entry.getKey().equals("width"))
			{
				imageToReturn.setWidth(entry.getValue());
			}
			if(entry.getKey().equals("height"))
			{
				imageToReturn.setHeight(entry.getValue());
			}
		}
	}
	

	public  String getFileSystemPath(EPublisherMedia file,String url)
    {
		logger.info("file: " + file);
		logger.info("Url: " + url);
		String path= tenantService.getTenantImageLocation(url);
		logger.info("path: " + path);
		
    return  path+ File.separator + file.getFolderid() + File.separator + file.getVersion();
    }

	
	public void validations(MultipartFile file, Integer x1, Integer y1, Integer x2, Integer y2, ImageSize imageSize)
			throws EpublisherException, IOException {
		
		BufferedImage originalImage = ImageIO.read(file.getInputStream());
		if ((imageSize==null)  && (!fileIsImage(FilenameUtils.getExtension(file.getOriginalFilename()))
				|| !xAxisHasTheRightSize(x1, x2,1024) || !yAxisHasTheRightSize(y1, y2,768) || !originalImageValidation(x1, x2, y1, y2,originalImage)
				|| !roundingError(x1, x2, y1, y2)))
		{
			throw new EpublisherException(
					"De afbeelding voldoet niet aan de eisen voor grootte, hoogte/breedte verhoudingen of bestands formaat");
		}
		if ((imageSize!=null) && (!fileIsImage(FilenameUtils.getExtension(file.getOriginalFilename()))
				|| !isImageDismensionRight(originalImage.getWidth(), originalImage.getHeight(), imageSize)))
		{
			throw new EpublisherException(
					"De afbeelding voldoet niet aan de eisen voor grootte, hoogte/breedte verhoudingen of bestands formaat");
		}
	}


	public BufferedImage cropImage(BufferedImage originalImage, Integer x1, Integer y1, Integer width, Integer height) 
	{
		return originalImage.getSubimage(x1, y1, width,height);
	}
	

	public byte[] getBufferedImageBytes(BufferedImage croppedImage) 
	{
		byte[] croppedImageInByte = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(croppedImage, "png", baos);
			baos.flush();
			croppedImageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			logger.debug(e.getMessage());
		} 
		return croppedImageInByte;
	}
	
	public BufferedImage getBufferedImage(byte[] bytes) throws IOException 
	{
		// Read the original cropped image
		InputStream inputStream = new ByteArrayInputStream(bytes);
		BufferedImage croppedOriginal = ImageIO.read(inputStream);
		inputStream.close();
		return croppedOriginal;
	}
	
	

	public BufferedImage convertBufferedImage(BufferedImage croppedImage,boolean hasAlpha) 
	{
		BufferedImage convertedImg =null;
		//Check for transparent image background  NS_EPB-1237
		 if (hasAlpha) {
			// Converts the buffered image to the required type (NS_EPB-842)
	    		 convertedImg = new BufferedImage(croppedImage.getWidth(), croppedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        } else {
	        	// Converts the buffered image to the required type (NS_EPB-842)
	    		 convertedImg = new BufferedImage(croppedImage.getWidth(), croppedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
	        }
		convertedImg.getGraphics().drawImage(croppedImage, 0, 0, null);
		convertedImg.getGraphics().dispose();
		return convertedImg;
	}
	
	
	public BufferedImage resizeBufferImage(BufferedImage croppedImage,int width, int height,boolean hasAlpha) 
	{
		BufferedImage convertedImg =null;
		 if (hasAlpha) {
			// Converts the buffered image to the required type (NS_EPB-842)
	    		 convertedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        } else {
	        	// Converts the buffered image to the required type (NS_EPB-842)
	    		 convertedImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	        }
		
		Graphics2D g = convertedImg.createGraphics();
		g.drawImage(croppedImage, 0, 0,width,height, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		return convertedImg;
	}
	
	private String getFileSystemPathFolder(EPublisherMedia file)
    {
        return EpublisherConstants.PATH + "/" + "1.0";
    }	
	public boolean fileIsImage(String ext) 
	{
		return ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("gif") ||ext.equalsIgnoreCase("jpeg")
				||ext.equalsIgnoreCase("pjpg")||ext.equalsIgnoreCase("pjpeg");
	}
	
	private boolean xAxisHasTheRightSize(Integer x1, Integer x2,Integer xAxis) 
	{
		return x2 >= xAxis;
	}
	
	private boolean yAxisHasTheRightSize(Integer y1, Integer y2,Integer yAxis) 
	{
		return y2 >= yAxis;
	}
	
	private boolean originalImageValidation(Integer x1, Integer x2, Integer y1, Integer y2,BufferedImage originalImage ) 
	{
		if((x1+x2<= originalImage.getWidth()) && (y1+y2<=originalImage.getHeight()))
		return true;
		
		return false;
	}
	
	
	private boolean isImageDismensionRight(Integer originalWidth,Integer originalHeight,ImageSize resizedImage)
	{
		if(originalWidth >= resizedImage.getWidth() && originalHeight >= resizedImage.getHeight())
		{
			return true;
		}
		return false;
	}
	private boolean roundingError(Integer x1, Integer x2, Integer y1, Integer y2) 
	{
		return (y2)/3 == (x2)/4;
	}

	@Transactional(rollbackFor = {EpublisherException.class})
	public EPublisherFile saveUploadedFile(EPublisherFile file,byte[] fileBytes, String url) throws EpublisherException, IOException 
	{
		EPublisherFile savedFile = fileDAO.saveUploadedFile(file);
		file.setFolderid(fileDAO.retriveSavedFile(savedFile.getId()));
		saveFile(file, fileBytes,url);
		return savedFile;
	}
	

	@Transactional(rollbackFor = {EpublisherException.class})
	public EPublisherImage saveUploadedImage(EPublisherImage imageToReturn,byte[] file, String url) throws EpublisherException, IOException
	{
		EPublisherImage savedImage = fileDAO.saveUploadedImage(imageToReturn);
		imageToReturn.setFolderid(fileDAO.retriveSavedFile(savedImage.getId()));
		saveFile(imageToReturn, file,url);
		return savedImage;
	}

	@Transactional(rollbackFor = { EpublisherException.class })
	public Map<String, Object> cropAndSaveImage(byte[] bytes, String caption,Long overlayX, Long overlayY,EPublisherImage resizedVariant, ImageSize imageSize,Map<String, Object> resultMap, String url)
			throws IOException, EpublisherException {
		List<EPublisherImage> thumbnails = new ArrayList<EPublisherImage>();
		Integer parentId;
		
		if(imageSize!=null){
			EPublisherImage epubImage = new EPublisherImage();
			// Copy the correct properties
			epubImage.setWidth(imageSize.getWidth());
			epubImage.setHeight(imageSize.getHeight());
			epubImage.setCaption((caption==null || caption.isEmpty()) ? "": caption);
			epubImage.setFileSizeInbytes(bytes.length);
			epubImage.setFileName(resizedVariant.getFileName());
			epubImage.setUsername(resizedVariant.getUsername());
			epubImage.setUrl(EpublisherConstants.URL_ROOT+epubImage.getUuid());
			epubImage.setVersion("1.0");
			epubImage.setName(imageSize.getName());
			
			EPublisherImage newThumbnail = saveUploadedImage(epubImage,bytes,url);
			epubImage.setFolderid(newThumbnail.getFolderid());
			thumbnails.add(newThumbnail);
		}
		else
		{
		EPublisherImage epubImageMaster=(EPublisherImage) resultMap.get("originalImage");
		logger.debug("reading original cropped image");

		// Read the original cropped image
		BufferedImage croppedOriginal =getBufferedImage(bytes);

		logger.debug("looping through all image cuts");
		
		// Loop through all imageCut enums
		for (ImageCuts imageCut : ImageCuts.values())
		{
			if(imageCut.name() != "FULL_1024")
			{
				parentId= epubImageMaster.getId();
				byte[] imageInBytes=cropSelectedImage(imageCut,croppedOriginal,overlayX,overlayY);
				resultMap=saveSelectedImage(imageCut, resizedVariant, parentId, caption, resultMap, thumbnails, imageInBytes,url);
			}
		}
		}
		resultMap.put("thumbnails", thumbnails);
		return resultMap;
	}
	
	public byte[] cropSelectedImage(ImageCuts imageCut,BufferedImage croppedOriginal, Long overlayX, Long overlayY
			 ) throws EpublisherException, IOException
	{

		logger.debug("Current cut " + imageCut.name() + " : " + imageCut.getResizeWidth() + " x " + imageCut.getResizeHeight());

		BufferedImage preCropResizedImage;

		// If the cropped original is the same dimensions as the current cut's preCrop dimensions, copy it
		if (imageCut.getPreCropResizeWidth().equals(croppedOriginal.getWidth()) && imageCut.getPreCropResizeHeight().equals(croppedOriginal.getHeight()))
		{
			logger.debug("same dimensions, not preCropResizing");
			preCropResizedImage = croppedOriginal;
		}
		// Otherwise, resize the croppedOriginal to the preCrop dimensions
		else
		{
			logger.debug("preCropResized to : " + imageCut.getPreCropResizeWidth() + " x " + imageCut.getPreCropResizeHeight());
			preCropResizedImage = Scalr.resize(croppedOriginal, Method.ULTRA_QUALITY, Mode.FIT_EXACT, imageCut.getPreCropResizeWidth(), imageCut.getPreCropResizeHeight());
		}

		// Crop the part the current cut wants.
		Integer startX = imageCut.getUseOffsetX() ? overlayX.intValue() : 0;
		Integer startY = imageCut.getUseOffsetY() ? overlayY.intValue() : 0;

		startX = imageCut.getUseOffsetX() && (imageCut.getRelativeToOverlayX() != 0) ? startX - imageCut.getRelativeToOverlayX() : startX;
		startY = imageCut.getUseOffsetY() && (imageCut.getRelativeToOverlayY() != 0) ? startY - imageCut.getRelativeToOverlayY() : startY;

		// verify that start X an Y not out of image bounds
		startX = startX < 0 ? 0 : startX;
		startY = startY < 0 ? 0 : startY;

		startX = startX + imageCut.getCropWidth() > EpublisherConstants.IMAGE_MAX_X_SIZE ? EpublisherConstants.IMAGE_MAX_X_SIZE - imageCut.getCropWidth() : startX;
		startY = startY + imageCut.getCropHeight() > EpublisherConstants.IMAGE_MAX_Y_SIZE ? EpublisherConstants.IMAGE_MAX_Y_SIZE - imageCut.getCropHeight() : startY;

		BufferedImage croppedCut = Scalr.crop(preCropResizedImage, startX, startY, imageCut.getCropWidth(), imageCut.getCropHeight());

		logger.debug("cropped " + imageCut.name() + " : " + croppedCut.getWidth() + " x " + croppedCut.getHeight());

		// Now resize the cropped cut to the final desired size.
		BufferedImage resizedCut = Scalr.resize(croppedCut, Method.ULTRA_QUALITY, Mode.FIT_EXACT, imageCut.getResizeWidth(), imageCut.getResizeHeight());

		logger.debug("resized cropped cut of " + imageCut.name() + " : " + resizedCut.getWidth() + " x " + resizedCut.getHeight());

		// Encode it to JPEG with 0.9 quality
		byte[] imageInBytes = encodeJPEG(resizedCut, 0.9F);

		logger.debug("Converted to jpg byte array: " + imageCut.name());

		return imageInBytes;
	
	}

	@Transactional(rollbackFor = { EpublisherException.class })
	public Map<String, Object> saveSelectedImage(ImageCuts imageCut,EPublisherImage resizedVariant,Integer parentId
			, String caption,	Map<String, Object> resultMap,List<EPublisherImage> thumbnails,byte[] imageInBytes, String url  ) throws EpublisherException, IOException
	{
		EPublisherImage epubImage = new EPublisherImage();
		
		// Copy the correct properties
		epubImage.setWidth(imageCut.getResizeWidth());
		epubImage.setHeight(imageCut.getResizeHeight());
		epubImage.setCaption((caption==null || caption.isEmpty()) ? "": caption);
		epubImage.setName(imageCut.name());
		epubImage.setGenerated(imageCut.getMarkAsGenerated());
		epubImage.setFileSizeInbytes(imageInBytes.length);
		epubImage.setFileName(resizedVariant.getFileName());
		epubImage.setUsername(resizedVariant.getUsername());
		epubImage.setUrl(EpublisherConstants.URL_ROOT+epubImage.getUuid());
		epubImage.setVersion("1.0");
		
		if (imageCut.getMarkAsGenerated())
			epubImage.setParentImageId(parentId);
		
		EPublisherImage newImage = saveUploadedImage(epubImage,imageInBytes,url);
		
		epubImage.setFolderid(newImage.getFolderid());

		if(epubImage.getName() == "FULL_1024")
		{
			resultMap.put("originalImage", epubImage);
		}
		else{
			thumbnails.add(epubImage);
		}
	
		logger.debug("added to result list: " + imageCut.name());
		logger.debug(epubImage.getUrl());
		return resultMap;
	
	}
	private static byte[] encodeJPEG(BufferedImage image, float quality) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageOutputStream iosout = new MemoryCacheImageOutputStream(out);

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(quality);

		writer.setOutput(iosout);

		IIOImage iioi = new IIOImage(image, null, null);
		writer.write(null, iioi, iwp);

		writer.dispose();

		return out.toByteArray();
	}


	public ImageSize getImageSize(Integer imageSizeId) {
		
		return fileDAO.getImageSize(imageSizeId);
	}
	
	public void imageSizeValidation(ImageSize imageSize) throws EpublisherException {
		
		if(imageSize.getName()!=null && imageSize.getWidth() != 0 && imageSize.getHeight()!=0)
		{
			if(!(imageSize.getWidth() <= 10240) || !(imageSize.getHeight()<=10240))
			{
				throw new EpublisherException(imageSize.getWidth() <= 10240? "Height exceeds maximum limit":"Width exceeds maximum limit");
			}
		}else{
			throw new EpublisherException("Alle velden zijn verplicht en de breedte en de hoogte moeten groter zijn dan 0");
		}
	}
	
	public BufferedImage rotate(BufferedImage image,int angle)
	{
		AffineTransform xform = new AffineTransform();
		BufferedImage newImage = null;
		if(angle== 90 || angle ==270)
		{
			if (image.getWidth() > image.getHeight())
			{
				xform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getWidth());
				xform.rotate(Math.toRadians(angle));

				int diff = image.getWidth() - image.getHeight();

				switch (angle)
				{
				case 90:
					xform.translate(-0.5 * image.getWidth(), -0.5 * image.getWidth() + diff);
					break;
				default:
					xform.translate(-0.5 * image.getWidth(), -0.5 * image.getWidth());
					break;
				}
			}
			else if (image.getHeight() > image.getWidth())
			{
				xform.setToTranslation(0.5 * image.getHeight(), 0.5 * image.getHeight());
				xform.rotate(Math.toRadians(angle));

				int diff = image.getHeight() - image.getWidth();

				switch (angle)
				{
				case 270:
					xform.translate(-0.5 * image.getHeight() + diff, -0.5 * image.getHeight());
					break;
				default:
					xform.translate(-0.5 * image.getHeight(), -0.5 * image.getHeight());
					break;
				}
			}
			else
			{
				xform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getHeight());
				xform.rotate(Math.toRadians(angle));
				xform.translate(-0.5 * image.getHeight(), -0.5 * image.getWidth());
			}
			AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BILINEAR);

			newImage =new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
			newImage =  op.filter(image, newImage);
		}else if(angle == 180){
			newImage=rotate180(image);
		}else{
			newImage= image;
		}
		return newImage;
	}
	public BufferedImage rotate180( BufferedImage inputImage ) {
		int width = inputImage.getWidth(); //the Width of the original image
		int height = inputImage.getHeight();//the Height of the original image

		BufferedImage returnImage = new BufferedImage( width, height, inputImage.getType()  );

		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				returnImage.setRGB(width - x - 1, height - y -1, inputImage.getRGB( x, y )  );
			}
		}
		return returnImage;
	} 
	
	
	public List<EPublisherMedia> getAvailableImages(String email, Integer itemsPerPage, Integer offset){
		
		return fileDAO.getAvailableImages(email, itemsPerPage, offset); 
	}
	
	public List<EPublisherImage> getImageById(Integer imageID){
		
		return fileDAO.getImageById(imageID); 
	}
	
}
