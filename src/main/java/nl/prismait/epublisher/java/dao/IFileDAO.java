package nl.prismait.epublisher.java.dao;

import java.util.List;

import nl.prismait.epublisher.java.model.EPublisherFile;
import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.EPublisherMedia;
import nl.prismait.epublisher.java.model.ImageSize;
import nl.prismait.epublisher.java.model.exception.EpublisherException;

public interface IFileDAO {

	public EPublisherFile saveUploadedFile(EPublisherFile file) throws EpublisherException;

	public Long retriveSavedFile(Integer id) throws EpublisherException;

	public EPublisherImage saveUploadedImage(EPublisherImage imageToReturn) throws EpublisherException;

	public ImageSize getImageSize(Integer imageSizeId);

	public List<EPublisherMedia> getAvailableImages(String email, Integer itemsPerPage, Integer offset);

	public List<EPublisherImage> getImageById(Integer imageID);

}
