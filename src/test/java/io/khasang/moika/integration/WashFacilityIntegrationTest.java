package io.khasang.moika.integration;

import io.khasang.moika.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class WashFacilityIntegrationTest {


    @Ignore
    @Before
    public void initTests() {
        System.out.println("Tests are beginning...");
    }

    final int id = 8;
    final String fcltName = "Test2 REST мойка";
    final String existingFasity = "Мойка на Помойке";
    final String statusCode = "WORKING";
    final String typeCode = "MEDIUM";
    private final String requestMapping = "http://localhost:8080/api";

    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createTestFacility() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        WashFacility fclt = new WashFacility(); // подготовили класс для тестирования
        BoxStatus boxStatus = new BoxStatus();
        BoxType boxType = new BoxType();

        HttpEntity httpEntity;

        fclt.setName(fcltName);
        fclt.setIdNet(1);
        fclt.setDescription("Тестовая RESTовая мойка");

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxStatus
        boxStatus = restTemplate.exchange(
                requestMapping + "/washBox/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxStatus.class, statusCode).getBody();
        Assert.assertNotNull("Could not get box status " + statusCode, boxStatus);

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                requestMapping + "/washBox/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxType.class, typeCode).getBody();
        Assert.assertNotNull("Could not get box type " + typeCode, boxType);

        List<WashBox> boxList = new ArrayList<>(); // Подготовили боксы
        for (int i = 1; i < 5; i++) {
            WashBox box = new WashBox();
            box.setBoxName("Тестовый Бокс № " + i);
            box.setDescription(box.getBoxName() + " " + fclt.getName());
            box.setBoxStatusEntity(boxStatus);
            box.setBoxTypeEntity(boxType);
            boxList.add(box);
        }
        fclt.setWashBoxes(boxList);

        City newCity = new City("Томск");
        WashAddr addr = new WashAddr();
        addr.setCity(newCity);
        addr.setStreet("Адмирала Колчака");
        addr.setBuilding("1919");

        httpEntity = new HttpEntity<>(addr, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        WashAddr resAddr = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping + "/washAddr/add",
                HttpMethod.POST,
                httpEntity,
                WashAddr.class).getBody();
        Assert.assertNotNull("Could not add address", resAddr);

        fclt.setIdAddr((int) resAddr.getId());
        fclt.setFacilityAddr(addr);

        httpEntity = new HttpEntity<>(fclt, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        WashFacility resFclt = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping + "/washFacility/add",
                HttpMethod.POST,
                httpEntity,
                WashFacility.class).getBody();

        boolean isBox = false;
         if (resFclt.getName().equalsIgnoreCase(fcltName)) {
            Assert.assertEquals("Facility  does not contain boxes", 4, resFclt.getWashBoxes().size());
            List<WashBox> resBoxList = resFclt.getWashBoxes();
            for (WashBox box : resBoxList) {
                isBox = true;
                Assert.assertTrue("Facility box status not " + statusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(statusCode));
                Assert.assertTrue("Facility box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
                break;
            }
        }
        Assert.assertTrue("Facility does not contain " + fcltName, resFclt.getName().equalsIgnoreCase(fcltName));
        Assert.assertTrue("Facility does not contain box", isBox);
    }


    @Test
    public void getFcltList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashFacility>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashFacility>> resultAll = restTemplate.exchange(
                requestMapping + "/washFacility/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashFacility>>() {
                });
        List<WashFacility> resList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain WashFacilities", resList.isEmpty());

        WashFacility resFclt = resList.get(0);
        Assert.assertNotNull("Could not get any facility from list", resFclt);

        Assert.assertTrue("Facility does  not contain boxes", resFclt.getWashBoxes().size() > 0);
        WashBox resBox = resFclt.getWashBoxes().get(0);
        Assert.assertNotNull("Could not get any box", resBox);
        BoxStatus bs = resBox.getBoxStatusEntity();
        Assert.assertNotNull("Could not get any box status entity", bs);
        BoxType bt = resBox.getBoxTypeEntity();
        Assert.assertNotNull("Could not get any box type entity", bt);
    }

    @Test
    public void getFcltById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        HttpEntity<List<WashFacility>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashFacility> resultAll = restTemplate.exchange(
                requestMapping + "/washFacility/byId/{id}/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashFacility>() {
                }, id);
        Assert.assertNotNull("Reques body is incorrect", resultAll);

        WashFacility resFclt = resultAll.getBody();
        Assert.assertNotNull("Request body does not contain WashFacilities", resFclt);

        Assert.assertTrue("Facility does not exist " + existingFasity, resFclt.getName().equalsIgnoreCase(existingFasity));
        boolean isBox = false;

        Assert.assertTrue("Facility does  not contain boxes", resFclt.getWashBoxes().size() > 0);
        List<WashBox> resBoxList = resFclt.getWashBoxes();
        Assert.assertEquals("Facility box list is not 4 item length", 4 , resBoxList.size());
        WashBox box = resBoxList.get(0);
        if (box.getBoxName().equalsIgnoreCase("Бокс № 1")) {
            isBox = true;
            Assert.assertTrue("Facility  box status not " + statusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(statusCode));
            Assert.assertTrue("Facility  box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
        }
        Assert.assertTrue("Facility does not contain box", isBox);
    }
}