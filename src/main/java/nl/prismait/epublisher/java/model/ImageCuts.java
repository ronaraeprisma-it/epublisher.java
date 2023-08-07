// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model;

public enum ImageCuts
{
	FULL_1024(1024, 768, false, false, 1024, 768, 1024, 768, false, false, 0, 0),
	FULL_960(1024, 768, false, false, 1024, 768, 960, 720, true, true, 0, 0),
	FULL_480(1024, 768, false, false, 1024, 768, 480, 360, true, true, 0, 0),
	FULL_315(1024, 768, false, false, 1024, 768, 315, 236, true, true, 0, 0),
	FULL_158(1024, 768, false, false, 1024, 768, 158, 118, true, true, 0, 0),

	WIDE_1024(1024, 768, false, true, 1024, 464, 1024, 464, true, true, 0, 0),
	WIDE_718(1024, 768, false, true, 1024, 464, 718, 326, true, true, 0, 0),
	WIDE_622(1024, 768, false, true, 1024, 464, 622, 282, true, true, 0, 0),
	WIDE_321(1024, 768, false, true, 1024, 464, 321, 146, true, true, 0, 0),

	SQUARE_176(1024, 768, true, true, 466, 466, 176, 176, true, true, 0, 0),
	SQUARE_88(1024, 768, true, true, 466, 466, 88, 88, true, true, 0, 0),

	CUSTOM_390_293(1024, 768, false, false, 1024, 768, 390, 293, true, true, 0, 0),

	CUSTOM_450_280(1024, 768, false, true, 1024, 638, 450, 280, true, true, 0, 86), // 2.275
	CUSTOM_310_230(1024, 768, false, true, 1024, 760, 310, 230, true, true, 0, 147), // 3.305
	CUSTOM_210_124(1024, 768, false, true, 1024, 604, 210, 124, true, true, 0, 69), // 4,87
	CUSTOM_390_230(1024, 768, false, true, 1024, 604, 390, 230, true, true, 0, 69), // 2,625

	CUSTOM_210_230(1024, 768, true, true, 426, 466, 210, 230, true, true, 10, 0), // 2.026
	CUSTOM_690_408(1024, 768, false, true, 1024, 624, 690, 408, true, true, 0, 79), // 1.484

	CUSTOM_560_260(1024, 768, true, true, 1024, 477, 560, 260, true, true, 0, 0), // 1.83
	CUSTOM_272_181(1024, 768, false, true, 1024, 681, 272, 181, true, true, 0, 0), // 3.76
	;
	
	private Integer preCropResizeWidth;
	private Integer preCropResizeHeight;
	private Boolean useOffsetX;
	private Boolean useOffsetY;
	private Integer cropWidth;
	private Integer cropHeight;
	private Integer resizeWidth;
	private Integer resizeHeight;
	private Boolean markAsGenerated;
	private Boolean addAsThumbnail;
	private Integer relativeToOverlayX; // cropWidth - SQUAREcropWidth[466] / 2 --- 0 ignore setting
	private Integer relativeToOverlayY; // cropHeight - SQUAREcropHeight[466] / 2 --- 0 ignore setting

	private ImageCuts(
		Integer preCropResizeWidth,
		Integer preCropResizeHeight,
		Boolean useOffsetX,
		Boolean useOffsetY,
		Integer cropWidth,
		Integer cropHeight,
		Integer resizeWidth,
		Integer resizeHeight,
		Boolean markAsGenerated,
			Boolean addAsThumbnail,
			Integer relativeToOverlayX,
			Integer relativeToOverlayY)
	{
		this.preCropResizeWidth = preCropResizeWidth;
		this.preCropResizeHeight = preCropResizeHeight;
		this.useOffsetX = useOffsetX;
		this.useOffsetY = useOffsetY;
		this.cropWidth = cropWidth;
		this.cropHeight = cropHeight;
		this.resizeWidth = resizeWidth;
		this.resizeHeight = resizeHeight;
		this.markAsGenerated = markAsGenerated;
		this.addAsThumbnail = addAsThumbnail;
		this.relativeToOverlayX = relativeToOverlayX;
		this.relativeToOverlayY = relativeToOverlayY;
	}

	public Integer getPreCropResizeWidth()
	{
		return preCropResizeWidth;
	}
	
	public void setPreCropResizeWidth(Integer preCropResizeWidth)
	{
		this.preCropResizeWidth = preCropResizeWidth;
	}
	
	public Integer getPreCropResizeHeight()
	{
		return preCropResizeHeight;
	}
	
	public void setPreCropResizeHeight(Integer preCropResizeHeight)
	{
		this.preCropResizeHeight = preCropResizeHeight;
	}
	
	public Boolean getUseOffsetX()
	{
		return useOffsetX;
	}
	
	public void setUseOffsetX(Boolean useOffsetX)
	{
		this.useOffsetX = useOffsetX;
	}
	
	public Boolean getUseOffsetY()
	{
		return useOffsetY;
	}
	
	public void setUseOffsetY(Boolean useOffsetY)
	{
		this.useOffsetY = useOffsetY;
	}
	
	public Integer getCropWidth()
	{
		return cropWidth;
	}
	
	public void setCropWidth(Integer cropWidth)
	{
		this.cropWidth = cropWidth;
	}
	
	public Integer getCropHeight()
	{
		return cropHeight;
	}
	
	public void setCropHeight(Integer cropHeight)
	{
		this.cropHeight = cropHeight;
	}
	
	public Integer getResizeWidth()
	{
		return resizeWidth;
	}

	public void setResizeWidth(Integer resizeWidth)
	{
		this.resizeWidth = resizeWidth;
	}
	
	public Integer getResizeHeight()
	{
		return resizeHeight;
	}
	
	public void setResizeHeight(Integer resizeHeight)
	{
		this.resizeHeight = resizeHeight;
	}
	
	public Boolean getMarkAsGenerated()
	{
		return markAsGenerated;
	}
	
	public void setMarkAsGenerated(Boolean markAsGenerated)
	{
		this.markAsGenerated = markAsGenerated;
	}

	public Boolean getAddAsThumbnail()
	{
		return addAsThumbnail;
	}
	
	public void setAddAsThumbnail(Boolean addAsThumbnail)
	{
		this.addAsThumbnail = addAsThumbnail;
	}

	public Integer getRelativeToOverlayX()
	{
		return relativeToOverlayX;
	}

	public void setRelativeToOverlayX(Integer relativeToOverlayX)
	{
		this.relativeToOverlayX = relativeToOverlayX;
	}

	public Integer getRelativeToOverlayY()
	{
		return relativeToOverlayY;
	}

	public void setRelativeToOverlayY(Integer relativeToOverlayY)
	{
		this.relativeToOverlayY = relativeToOverlayY;
	}
}