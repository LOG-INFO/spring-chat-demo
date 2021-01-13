package info.log.demo.common

import org.springframework.web.multipart.MultipartFile

class ImageUploadUtil {
    companion object {
        //TODO: 이미지 업로드 후 경로 반환
        fun uploadImage(image: MultipartFile?): String? {
            if (image == null) {
                return null
            }
            return "이미지 경로"
        }
    }
}