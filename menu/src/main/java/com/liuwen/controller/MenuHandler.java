package com.liuwen.controller;

import com.liuwen.entity.Menu;
import com.liuwen.entity.MenuVO;
import com.liuwen.entity.Type;
import com.liuwen.repository.MenuRepository;
import com.liuwen.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private TypeRepository typeRepository;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }

//    @GetMapping("/findAll/{page}/{limit}")
//    public List<Menu> findAll(@PathVariable("page") int index, @PathVariable("limit") int limit){
//       return menuRepository.findAll(index,limit);
//    }

    @GetMapping("/findAll/{page}/{limit}")
    public MenuVO findAll(@PathVariable("page") int index, @PathVariable("limit") int limit){
        return new MenuVO(0,"",menuRepository.count(),menuRepository.findAll(index, limit));
    }


    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    //    注解@RequestBody将json格式的数据转换为menu对象，要不然就只能拿到一个空的对象。
    public void save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menuRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }
}
