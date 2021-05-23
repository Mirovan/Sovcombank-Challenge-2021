//package ru.bigint;
//
//import ru.bigint.model.Point;
//import ru.bigint.util.LoggerUtil;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class Main {
//
//    private static ExecutorService threadPool = Executors.newFixedThreadPool(Constant.threadsCount);
//
//    static final Object lockDig = new Object();
//
//
//    public static void main(String[] args) {
//        try {
//            long time = System.currentTimeMillis();
//            LoggerUtil.log("--- VERSION : " + Constant.version + " ---");
//            Main main = new Main();
//            main.runGame();
////            System.out.println((float) (System.currentTimeMillis() - time) / 1000.0f);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void runGame() {
//
//        CompletableFuture<List<String>> cf = CompletableFuture
//                .supplyAsync(() -> Actions.dig(client.getLicenseWrapperList(), pointStack), threadPoolDig)
//                .thenApply(dig -> {
//                    List<String> res = null;
//                    if (dig != null) {
//
//                        //выкопать не удалось - лицензию не потратили
//                        if (dig.getResponse() == null) {
//                            synchronized (lockDig) {
//                                client.getLicenseWrapperList().stream()
//                                        .filter(item -> dig.getLicence() != null && item.getLicense().getId().equals(dig.getLicence().getId()))
//                                        .findFirst()
//                                        .ifPresent(licenseWrapper -> licenseWrapper.setUseCount(licenseWrapper.getUseCount() - 1));
//                            }
//                        }
//                        //Если что-то выкопали (может и пустое)
//                        else {
//                            synchronized (lockDig) {
//                                int licId = dig.getLicence().getId();
//
//                                //Обновляем лицензию в общем списке
//                                client.getLicenseWrapperList().stream()
//                                        .filter(item -> item.getLicense().getId().equals(licId))
//                                        .findFirst()
//                                        .ifPresent(item -> item.getLicense().setDigUsed(item.getLicense().getDigUsed() + 1));
//                            }
//
//                            //обновляем точку
//                            Point point = dig.getPoint();
//                            point.setDepth(point.getDepth() + 1);
//                            point.setTreasuresCount(point.getTreasuresCount() - dig.getResponse().length);
//
//                            //Помещаем обратно точку в стек - если сокровища еще есть
//                            if (point.getTreasuresCount() > 0) {
//                                pointStack.add(point);
//                            }
//
//                            //Сохраняем в коллекцию сокровища
//                            res = Arrays.asList(dig.getResponse());
//                        }
//                    }
//
//                    return res;
//                });
//        cfTreasuresList.add(cf);
//
//
//        LoggerUtil.log("FINISH");
//    }
//
//}