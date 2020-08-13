package com.example.rest.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.rest.bean.Logger;
import com.example.rest.repo.LoggerRepository;

@RestController
public class LoggerController {
	@Autowired
	LoggerRepository loggerRepository;

	// GETリクエスの対応
	@RequestMapping(value = "/logger", method = RequestMethod.GET)
	public List<Logger> loggerGet() {
		// SELECT * FROM loggerが実行
		List<Logger> list = loggerRepository.findAll();
		return list;
	}

	// POSTリクエストによってloggerテーブルにデータをINSERTする
	@RequestMapping(value = "/logger", method = RequestMethod.POST)
	public List<Logger> loggerPost(
			@RequestBody List<Logger> loggerList) {

		// INSERT INTO loggerが実行
		List<Logger> result = loggerRepository.saveAll(loggerList);
		return result;
	}

	// PUTリクエストによってloggerテーブを更新する
	@RequestMapping(value = "/logger", method = RequestMethod.PUT)
	public Logger loggerPut(@RequestBody Logger logger) {
		Optional<Logger> target = loggerRepository.findById(logger.getId());
		if (target.isEmpty()) {
			// 指定されたIDが見つからない場合は何もせず終了
			return null;
		} else {
			loggerRepository.save(logger);
			return target.get();
		}
	}

	// DELETEリクエストによってloggerテーブを削除する
	@RequestMapping(value = "/logger", method = RequestMethod.DELETE)
	public Logger loggerDelete(@RequestBody Logger logger) {
		Optional<Logger> target = loggerRepository.findById(logger.getId());
		if (target.isEmpty()) {
			// 指定されたIDが見つからない場合は何もせず終了
			return null;
		} else {
			//ｌoggerテーブを削除する
			loggerRepository.deleteById(target.get().getId());
			// 削除したデータを返す。
			return target.get();
		}
	}
}
