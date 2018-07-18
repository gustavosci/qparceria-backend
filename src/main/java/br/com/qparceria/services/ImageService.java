package br.com.qparceria.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.qparceria.services.exceptions.FileException;

@Service
public class ImageService {

	private final String EXT_PNG = "png";
	private final String EXT_JPG = "jpg";
	
	public BufferedImage getJpgImagemFromMultipartFile(MultipartFile file) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if(!EXT_PNG.equalsIgnoreCase(ext) && !EXT_JPG.equalsIgnoreCase(ext)) {
			throw new FileException("Somente imagens PNG e JPG podem ser utilizadas");
		}
		
		try {
			BufferedImage img = ImageIO.read(file.getInputStream());
			if(EXT_PNG.equalsIgnoreCase(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler imagem");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String ext) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, ext, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao obter InputStream");		}
	}
}
