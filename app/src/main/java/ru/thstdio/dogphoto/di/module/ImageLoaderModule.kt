package ru.thstdio.dogphoto.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.repo.IRepoImage
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import ru.thstdio.dogphoto.util.imageloader.glide.GlideLoader
import ru.thstdio.dogphoto.util.imageloader.picaso.PicasoLoader
import javax.inject.Named

@Module
class ImageLoaderModule {
    @Provides
    fun setLoader(imageRepo: IRepoImage,@Named("mainTread") mainThread: Scheduler): IImageLoader {
//        return PicasoLoader(imageRepo, mainThread)
        return GlideLoader(imageRepo, mainThread)
    }



}