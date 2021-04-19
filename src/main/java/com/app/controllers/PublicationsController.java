package com.app.controllers;

import com.app.controllers.dtos.PublicationDTO;
import com.app.entities.Publication;
import com.app.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class PublicationsController {

    @Autowired
    PublicationService publicationService;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/pubs/privet")
    public String greeting() {
        return "privet";
    }

    /**
     * @return Publication list, no authentication needed
     */
    @GetMapping(value = "/getPubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PublicationDTO> getPubs() {

        List<PublicationDTO> list = new ArrayList<>();

        Path destinationFile = Paths.get("/imageDir");

        publicationService.findAllBy().forEach(publication -> {
            PublicationDTO dto = new PublicationDTO(publication.getId(), publication.getContent(), publication.getHeadline()); // creating publication dto instance, setting values from db model
            if (publication.getDate() != null){
                dto.setDate(new SimpleDateFormat("dd.MM.yyyy").format(publication.getDate())); // setting formatted date if it's existing
            }

            /*
            setting image in publication dto, if image exists in filesystem
             */

            Path filePath = destinationFile.resolve("image" + publication.getId() + ".jpg");
            try {
                byte[] fileContent = Files.readAllBytes(filePath);
                dto.setImage("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(fileContent));
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(dto); // adding dto to list
        });
        return list;
    }


    /**
     * Creating new Publication and storing it (string values in db, image in filesystem)
     * @param dto {@link PublicationDTO}
     * @throws IOException
     */
    @PostMapping(value = "/pubs/addPub", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPub(@RequestBody PublicationDTO dto) throws IOException {

        Publication publication = new Publication(dto.getHeadline(), dto.getContent(), new Date()); //creating model instance with values from dto
        publicationService.save(publication); // adding new db row

        /*
         storing image in filesystem
         */
        storeImage(dto.getImage(), publication.getId());
    }

    /**
     * Editing existing publication (its db row and image)
     * @param dto {@link PublicationDTO}
     * @throws IOException
     */
    @PostMapping(value = "/pubs/editPub", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editPub(@RequestBody PublicationDTO dto) throws IOException {

        /*
        getting and editing existing db row
         */
        Publication publication = publicationService.getById(dto.getId());
        publication.setContent(dto.getContent());
        publication.setHeadline(dto.getHeadline());
        publicationService.save(publication);

        /*
        getting and replacing image, if any image was received
         */
        storeImage(dto.getImage(), dto.getId());
    }

    /**
     * Deletion of db row and image in filesystem via Publication id
     * @param id Publication id
     */
    @PostMapping("/pubs/deletePub")
    public void deletePub(@RequestParam Integer id) {
        Publication publication = publicationService.getById(id); //getting publication via id
        publicationService.delete(publication); // deletion of publication
        /*
        getting and deletion of image
         */
        Path filePath = Paths.get("/imageDir").resolve("image" + id + ".jpg");
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Storing image in filesystem
     * @param image image byte sequence
     * @param id id of publication
     * @throws IOException
     */
    private void storeImage(String image, Integer id) throws IOException {

        String base64Image = image.split(",")[1];

        byte[] decodedImg = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get("/imageDir");

        if (!Files.exists(destinationFile)) {
            Files.createDirectories(destinationFile);
        }

        try (InputStream inputStream = new ByteArrayInputStream(decodedImg)) {
            Path filePath = destinationFile.resolve("image" + id + ".jpg");
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + "image" + id + ".jpg", ioe);
        }

    }


    @RequestMapping(value = "/{[path:[^\\.]*}")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/");
    }

}
