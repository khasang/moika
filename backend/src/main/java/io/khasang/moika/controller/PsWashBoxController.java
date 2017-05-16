package io.khasang.moika.controller;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.BoxStatusDataAccessService;
import io.khasang.moika.service.BoxTypesDataAccessService;
import io.khasang.moika.service.PskvorWashBoxDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Контроллер для управления боксами автомоек
 *
 * @author Pauls
 */
@Controller
@RequestMapping(value = "/api/washBox")
public class PsWashBoxController {

    @Autowired
    PskvorWashBoxDataAccessService pskvorWashBoxDataAccessService;

    @Autowired
    BoxStatusDataAccessService boxStatusDataAccessService;

    @Autowired
    BoxTypesDataAccessService boxTypesDataAccessService;

    /**
     * Вывод информации о всех боксах
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBoxList() {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getAllWashBoxes();
        if (washBoxList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washBoxList;
    }

    /**
     * Добавление бокса
     *
     * @param washBox
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashBox(@RequestBody WashBox washBox) {
        WashBox retWashBox = pskvorWashBoxDataAccessService.addWashBox(washBox);
        if (retWashBox == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return retWashBox;
    }

    /**
     * Обновление информации о боксе
     *
     * @param washBox
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWashBox(@RequestBody WashBox washBox) {
        WashBox retWashBox = pskvorWashBoxDataAccessService.updateWashBox(washBox);
        if (retWashBox == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return retWashBox;
    }

    /**
     * Выаод информаии о боксе по id
     *
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBox(@PathVariable(value = "id") String inputId) {
        WashBox washBox = pskvorWashBoxDataAccessService.getWashBoxById(Integer.valueOf(inputId));
        if (washBox == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washBox;
    }

    /**
     * Вывод информации о боксе по имени на конкретной мойке
     *
     * @param idFclt   - id мойки
     * @return
     */
    @RequestMapping(value = "/inFacility/{idFacility}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBoxesOnFacility(@PathVariable(value = "idFacility") int idFclt) {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesOnFacility(idFclt);
        if (washBoxList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washBoxList;
    }

    /**
     * Удаление бокса по id
     *
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashBox(@PathVariable(value = "id") String inputId) {
        WashBox washBox = pskvorWashBoxDataAccessService.getWashBoxById(Integer.valueOf(inputId));
        if (washBox != null) {
            int id = washBox.getId();
            pskvorWashBoxDataAccessService.deleteWashBox(washBox);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * вывод списка  боксов по их типам
     *
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/ByType/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBoxListbyType(@PathVariable(value = "type") String typeId) {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesByType(Integer.valueOf(typeId));
        if (washBoxList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washBoxList;
    }

    /**
     * вывод списка боксов по их статусам
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/ByStatus/{status}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBoxListbyStatus(@PathVariable(value = "status") String status) {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesByStatus(Integer.valueOf(status));
        if (washBoxList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washBoxList;
    }

    /**
     * выод статутсов боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxStatusList() {
        List<BoxStatus> statusList = boxStatusDataAccessService.getAllStatuses();
        if (statusList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return statusList;
    }

    /**
     * Список типов боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxTypesList(HttpServletResponse response) {
        List<BoxType> typeList = boxTypesDataAccessService.getAllTypes();
        if (typeList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return typeList;
    }


    /**
     * статус бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxStatus/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxStatusByCode(@PathVariable(value = "code") String code) {
        BoxStatus boxStatus = (BoxStatus)boxStatusDataAccessService.getStatusByCode(code);
        if (boxStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return boxStatus;
    }

    /**
     * тип бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxType/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxTypeByCode(@PathVariable(value = "code") String code) {
        BoxType boxType =  (BoxType) boxTypesDataAccessService.getTypeByCode(code);
        if (boxType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return boxType;
    }

    /**
     * статус бокса по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/boxStatus/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxStatusById(@PathVariable(value = "id") int id) {
        BoxStatus boxStatus =  (BoxStatus) boxStatusDataAccessService.getStatusById(id);
        if (boxStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return boxStatus;
    }

    /**
     * тип бокса по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/boxType/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getBoxTypeById(@PathVariable(value = "id") int id) {
        BoxType boxType = (BoxType) boxTypesDataAccessService.getTypeById(id);
        if (boxType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return boxType;
    }

    /**
     * Добавление нового статуса боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addNewBoxStatus(@RequestBody BoxStatus newBoxStatus) {
        BoxStatus boxStatus =  (BoxStatus) boxStatusDataAccessService.addStatus(newBoxStatus);
        if (boxStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return boxStatus;
    }

    /**
     * Добавление нового типа боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addNewBoxType(@RequestBody BoxType newBoxType) {
        BoxType boxType = (BoxType) boxTypesDataAccessService.addType(newBoxType);
        if (boxType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return boxType;
    }

    /**
     * Обновление нового статуса боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateBoxStatus(@RequestBody BoxStatus newBoxStatus) {
        BoxStatus boxStatus = (BoxStatus) boxStatusDataAccessService.updateStatus(newBoxStatus);
        if (boxStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return boxStatus;
    }

    /**
     * Обновление нового типа боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateBoxType(@RequestBody BoxType newBoxType) {
        BoxType boxType =  (BoxType) boxTypesDataAccessService.updateType(newBoxType);
        if (boxType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return boxType;
    }

    /**
     * Удаление статуса боксов
     *
     * @return - HTTPResponce status
     */
    @RequestMapping(value = "/boxStatus/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteBoxStatus(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        BoxStatus boxStatus = (BoxStatus) boxStatusDataAccessService.getStatusById(Integer.valueOf(inputId));
        if (boxStatus != null) {
            int id = boxStatus.getId();
            boxStatusDataAccessService.deleteStatus(boxStatus);
            return  new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление типа боксов
     *
     * @return -  HTTPResponce status
     */
    @RequestMapping(value = "/boxType/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteBoxType(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        BoxType boxType = (BoxType) boxTypesDataAccessService.getTypeById(Integer.valueOf(inputId));
        if (boxType != null) {
            int id = boxType.getId();
            boxTypesDataAccessService.deleteType(boxType);
            return  new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
